package com.unshoo.pixelmusic.utils

import android.content.Context
import android.os.Build
import com.unshoo.pixelmusic.data.remote.youtube.YoutubeHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Request
import java.io.File

object ShareVideoEngine {
    // Highly reliable, statically compiled FFmpeg binary for modern 64-bit Android devices
    private const val FFMPEG_URL_ARM64 = "https://github.com/Khang-NT/ffmpeg-binary-android/releases/download/v4.3.2/ffmpeg-aarch64"

    suspend fun downloadEngineIfNeeded(context: Context, onProgress: (String) -> Unit): File? = withContext(Dispatchers.IO) {
        val engineDir = File(context.filesDir, "engines")
        engineDir.mkdirs()
        
        val ffmpegFile = File(engineDir, "ffmpeg_bin")
        // If it exists and is appropriately sized (~15MB), it's ready to go
        if (ffmpegFile.exists() && ffmpegFile.length() > 10_000_000L) { 
            return@withContext ffmpegFile
        }

        // Failsafe: Only download on 64-bit devices (99% of phones today)
        if (!Build.SUPPORTED_ABIS.contains("arm64-v8a")) {
            withContext(Dispatchers.Main) { onProgress("Device architecture not supported for video rendering.") }
            return@withContext null
        }

        withContext(Dispatchers.Main) { onProgress("Downloading video engine (15MB)... This only happens once.") }

        try {
            val request = Request.Builder().url(FFMPEG_URL_ARM64).build()
            val response = YoutubeHelper.client.newCall(request).execute()

            if (!response.isSuccessful) {
                withContext(Dispatchers.Main) { onProgress("Failed to connect to download server.") }
                return@withContext null
            }

            ffmpegFile.outputStream().use { out ->
                response.body?.byteStream()?.copyTo(out)
            }
            
            // CRITICAL: Android's security policy will block execution unless we explicitly grant this permission
            ffmpegFile.setExecutable(true)
            return@withContext ffmpegFile
        } catch (e: Exception) {
            e.printStackTrace()
            if (ffmpegFile.exists()) ffmpegFile.delete()
            withContext(Dispatchers.Main) { onProgress("Download failed: ${e.localizedMessage}") }
            return@withContext null
        }
    }

    suspend fun createShareVideo(
        context: Context,
        imagePath: String, 
        audioPath: String, 
        outputPath: String,
        onProgress: (String) -> Unit
    ): Boolean = withContext(Dispatchers.IO) {
        val ffmpeg = downloadEngineIfNeeded(context, onProgress) ?: return@withContext false

        withContext(Dispatchers.Main) { onProgress("Rendering video for Instagram/WhatsApp...") }

        try {
            val process = ProcessBuilder(
                ffmpeg.absolutePath,
                "-loop", "1",
                "-framerate", "1",
                "-i", imagePath,
                "-ss", "00:00:30", // Start grabbing audio at the 30-second mark (usually the "hook")
                "-t", "15",        // Grab exactly 15 seconds for social media stories
                "-i", audioPath,
                "-c:v", "libx264",
                "-tune", "stillimage",
                "-c:a", "aac",     // Force AAC encoding (Instagram/WhatsApp hate Opus/WebM audio)
                "-b:a", "128k",    // Standard bitrate for social media
                "-pix_fmt", "yuv420p", // Required pixel format for hardware decoders on social apps
                "-shortest",       // Stop video when the 15s audio clip ends
                "-y",              // Overwrite output file if it exists
                outputPath
            )
            .redirectErrorStream(true)
            .start()

            // Block until the render is complete
            val exitCode = process.waitFor()
            return@withContext exitCode == 0
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) { onProgress("Render crashed: ${e.localizedMessage}") }
            return@withContext false
        }
    }
}
