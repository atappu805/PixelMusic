package com.unshoo.pixelmusic.presentation.model

import com.unshoo.pixelmusic.presentation.navigation.Screen

data class SearchableSetting(
    val title: String,
    val subtitle: String,
    val keywords: List<String>,
    val category: SettingsCategory?,
    val route: String
)

/**
 * Static index of every user-facing setting in the app.
 * Add new entries here when you add new settings, and they'll automatically
 * become searchable.
 */
object SettingsSearchCatalog {

    val entries: List<SearchableSetting> = listOf(

        // ─── Appearance ─────────────────────────────────────────────────────────────
        SearchableSetting(
            title = "App Theme",
            subtitle = "Light, Dark, or Follow System",
            keywords = listOf("theme", "dark", "light", "night", "day", "mode"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.APPEARANCE.id)
        ),
        SearchableSetting(
            title = "AMOLED Black",
            subtitle = "Pure black background in dark mode",
            keywords = listOf("amoled", "black", "oled", "pure dark", "battery"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.APPEARANCE.id)
        ),
        SearchableSetting(
            title = "App Font",
            subtitle = "PixelMusic font or system font",
            keywords = listOf("font", "typeface", "text", "typography"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.APPEARANCE.id)
        ),
        SearchableSetting(
            title = "Color Palette",
            subtitle = "Dynamic, Sage, Purple, Blue, Orange, Yellow",
            keywords = listOf("color", "palette", "dynamic", "material you", "theme"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.APPEARANCE.id)
        ),
        SearchableSetting(
            title = "Player Design Style",
            subtitle = "Default, Immersive, or Immersive Extended",
            keywords = listOf("player", "design", "immersive", "now playing", "layout"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.APPEARANCE.id)
        ),
        SearchableSetting(
            title = "Language",
            subtitle = "Change the app display language",
            keywords = listOf("language", "locale", "english", "translate"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.APPEARANCE.id)
        ),
        SearchableSetting(
            title = "Motion Blur",
            subtitle = "Cinematic blur when scrolling lists",
            keywords = listOf("motion", "blur", "scroll", "animation"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.APPEARANCE.id)
        ),
        SearchableSetting(
            title = "Smooth Corners",
            subtitle = "Softer rounded corner shapes",
            keywords = listOf("corner", "rounded", "smooth", "shape", "curves"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.APPEARANCE.id)
        ),
        SearchableSetting(
            title = "Launch Tab",
            subtitle = "Tab the app opens to by default",
            keywords = listOf("launch", "start", "default", "home", "open"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.APPEARANCE.id)
        ),
        SearchableSetting(
            title = "Collage Pattern",
            subtitle = "Home screen album art collage layout",
            keywords = listOf("collage", "home", "pattern", "layout", "grid"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.APPEARANCE.id)
        ),
        SearchableSetting(
            title = "Carousel Style",
            subtitle = "Peek mode for album carousels",
            keywords = listOf("carousel", "peek", "album"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.APPEARANCE.id)
        ),
        SearchableSetting(
            title = "Palette Style",
            subtitle = "Album art color palette & accuracy",
            keywords = listOf("palette", "color", "style", "accuracy", "album art"),
            category = SettingsCategory.APPEARANCE,
            route = Screen.PaletteStyle.route
        ),

        // ─── Playback ───────────────────────────────────────────────────────────────
        SearchableSetting(
            title = "Dynamic Island",
            subtitle = "Live track pill in the status bar",
            keywords = listOf("dynamic island", "status bar", "origin os", "notch", "pill"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "AOD Screen",
            subtitle = "Ambient glowing Now Playing view",
            keywords = listOf("aod", "always on", "ambient", "glow", "amoled"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Crossfade",
            subtitle = "Smooth transition between tracks",
            keywords = listOf("crossfade", "transition", "gapless", "smooth"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Hi-Fi Mode",
            subtitle = "High-quality audio playback",
            keywords = listOf("hifi", "high quality", "lossless", "audio", "hi-res"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Replay Gain",
            subtitle = "Normalize volume across tracks",
            keywords = listOf("replaygain", "volume", "normalize", "loudness", "gain"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Streaming Audio Quality",
            subtitle = "Bitrate for Wi-Fi and mobile streaming",
            keywords = listOf("streaming", "quality", "bitrate", "audio", "wifi", "mobile"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Headphone Resume",
            subtitle = "Resume playback when headphones reconnect",
            keywords = listOf("headphone", "resume", "headset", "bluetooth", "jack"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Persistent Shuffle",
            subtitle = "Remember shuffle state between sessions",
            keywords = listOf("shuffle", "persistent", "random"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Preload Queue",
            subtitle = "Pre-buffer upcoming tracks",
            keywords = listOf("preload", "buffer", "queue", "cache"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Auto Queue",
            subtitle = "Continue with recommendations",
            keywords = listOf("auto queue", "autoqueue", "recommend", "continue", "radio"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Avoid Repetitive Songs",
            subtitle = "Don't repeat songs too often",
            keywords = listOf("avoid", "repeat", "repetitive", "duplicate"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Pure YouTube Music",
            subtitle = "Filter out non-music video content",
            keywords = listOf("pure", "music", "filter", "video", "youtube"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Download on Like",
            subtitle = "Auto-download liked YouTube songs",
            keywords = listOf("download", "like", "offline", "favorite"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),
        SearchableSetting(
            title = "Battery Optimization",
            subtitle = "Allow playback in the background",
            keywords = listOf("battery", "optimization", "background", "keep alive"),
            category = SettingsCategory.PLAYBACK,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.PLAYBACK.id)
        ),

        // ─── Library ────────────────────────────────────────────────────────────────
        SearchableSetting(
            title = "Excluded Directories",
            subtitle = "Folders to ignore while scanning",
            keywords = listOf("excluded", "folders", "directories", "ignore", "scan", "hide"),
            category = SettingsCategory.LIBRARY,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LIBRARY.id)
        ),
        SearchableSetting(
            title = "Artist Settings",
            subtitle = "Multi-artist parsing & delimiters",
            keywords = listOf("artist", "delimiters", "parsing", "group", "multi"),
            category = SettingsCategory.LIBRARY,
            route = Screen.ArtistSettings.route
        ),
        SearchableSetting(
            title = "Full Rescan",
            subtitle = "Rescan all music files from scratch",
            keywords = listOf("rescan", "refresh", "library", "sync", "scan", "index"),
            category = SettingsCategory.LIBRARY,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LIBRARY.id)
        ),
        SearchableSetting(
            title = "Rebuild Database",
            subtitle = "Clear and rebuild the library",
            keywords = listOf("rebuild", "database", "clear", "reset"),
            category = SettingsCategory.LIBRARY,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LIBRARY.id)
        ),
        SearchableSetting(
            title = "Min Song Duration",
            subtitle = "Skip tracks shorter than this",
            keywords = listOf("duration", "minimum", "short", "skip"),
            category = SettingsCategory.LIBRARY,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LIBRARY.id)
        ),
        SearchableSetting(
            title = "Min Tracks per Album",
            subtitle = "Hide albums with fewer tracks",
            keywords = listOf("album", "tracks", "minimum", "hide"),
            category = SettingsCategory.LIBRARY,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LIBRARY.id)
        ),
        SearchableSetting(
            title = "Album Art Cache Limit",
            subtitle = "Maximum size of the art cache",
            keywords = listOf("album art", "cache", "limit", "storage", "size"),
            category = SettingsCategory.LIBRARY,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LIBRARY.id)
        ),
        SearchableSetting(
            title = "Clear Streaming Cache",
            subtitle = "Free up space used by cached audio",
            keywords = listOf("cache", "clear", "storage", "space", "clean"),
            category = SettingsCategory.LIBRARY,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LIBRARY.id)
        ),
        SearchableSetting(
            title = "Auto Scan LRC Files",
            subtitle = "Discover local .lrc lyrics files",
            keywords = listOf("lyrics", "lrc", "auto scan", "subtitles"),
            category = SettingsCategory.LIBRARY,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LIBRARY.id)
        ),
        SearchableSetting(
            title = "Lyrics Source Priority",
            subtitle = "Embedded, Online, or Local first",
            keywords = listOf("lyrics", "source", "priority", "embedded", "api", "local"),
            category = SettingsCategory.LIBRARY,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LIBRARY.id)
        ),
        SearchableSetting(
            title = "Reset Imported Lyrics",
            subtitle = "Clear all imported lyrics",
            keywords = listOf("lyrics", "reset", "clear", "imported"),
            category = SettingsCategory.LIBRARY,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LIBRARY.id)
        ),

        // ─── Content ────────────────────────────────────────────────────────────────
        SearchableSetting(
            title = "Content Language",
            subtitle = "Preferred language for YouTube Music",
            keywords = listOf("language", "content", "youtube", "region"),
            category = SettingsCategory.CONTENT,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.CONTENT.id)
        ),
        SearchableSetting(
            title = "Content Country",
            subtitle = "Region preference for YouTube Music",
            keywords = listOf("country", "region", "content", "location"),
            category = SettingsCategory.CONTENT,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.CONTENT.id)
        ),
        SearchableSetting(
            title = "Hide Explicit",
            subtitle = "Hide tracks marked as explicit",
            keywords = listOf("explicit", "hide", "clean", "censored"),
            category = SettingsCategory.CONTENT,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.CONTENT.id)
        ),
        SearchableSetting(
            title = "Hide Video",
            subtitle = "Hide video content from results",
            keywords = listOf("video", "hide", "audio only"),
            category = SettingsCategory.CONTENT,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.CONTENT.id)
        ),
        SearchableSetting(
            title = "Quick Picks",
            subtitle = "Discover, Last Listen, or Don't Show",
            keywords = listOf("quick picks", "discover", "recommendations", "suggestions"),
            category = SettingsCategory.CONTENT,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.CONTENT.id)
        ),
        SearchableSetting(
            title = "Quick Picks Display",
            subtitle = "Card carousel or List grid",
            keywords = listOf("quick picks", "display", "card", "list", "layout"),
            category = SettingsCategory.CONTENT,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.CONTENT.id)
        ),
        SearchableSetting(
            title = "Playlist Suggestions Source",
            subtitle = "Playlist title, content, or both",
            keywords = listOf("playlist", "suggestion", "recommend"),
            category = SettingsCategory.CONTENT,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.CONTENT.id)
        ),

        // ─── Behavior ───────────────────────────────────────────────────────────────
        SearchableSetting(
            title = "Folder Back Gesture",
            subtitle = "Navigate up a folder with back gesture",
            keywords = listOf("folder", "back", "gesture", "navigate"),
            category = SettingsCategory.BEHAVIOR,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.BEHAVIOR.id)
        ),
        SearchableSetting(
            title = "Tap Background to Close",
            subtitle = "Close Now Playing by tapping outside",
            keywords = listOf("tap", "background", "close", "player", "dismiss"),
            category = SettingsCategory.BEHAVIOR,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.BEHAVIOR.id)
        ),
        SearchableSetting(
            title = "Haptic Feedback",
            subtitle = "Vibration on interactions",
            keywords = listOf("haptic", "vibrate", "feedback", "touch"),
            category = SettingsCategory.BEHAVIOR,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.BEHAVIOR.id)
        ),

        // ─── AI ─────────────────────────────────────────────────────────────────────
        SearchableSetting(
            title = "AI Provider",
            subtitle = "Gemini, DeepSeek, Groq, Mistral & more",
            keywords = listOf("ai", "provider", "gemini", "deepseek", "groq", "openai", "mistral"),
            category = SettingsCategory.AI_INTEGRATION,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.AI_INTEGRATION.id)
        ),
        SearchableSetting(
            title = "AI API Key",
            subtitle = "Credentials for your AI provider",
            keywords = listOf("ai", "api key", "credentials", "token", "key"),
            category = SettingsCategory.AI_INTEGRATION,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.AI_INTEGRATION.id)
        ),
        SearchableSetting(
            title = "AI System Prompt",
            subtitle = "Customize how the AI responds",
            keywords = listOf("ai", "system prompt", "personality", "customize"),
            category = SettingsCategory.AI_INTEGRATION,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.AI_INTEGRATION.id)
        ),
        SearchableSetting(
            title = "AI Usage Report",
            subtitle = "Token usage statistics & history",
            keywords = listOf("ai", "usage", "tokens", "statistics", "report"),
            category = SettingsCategory.AI_INTEGRATION,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.AI_INTEGRATION.id)
        ),
        SearchableSetting(
            title = "Safe Token Limit",
            subtitle = "Prevent excessive token usage",
            keywords = listOf("ai", "token", "limit", "safe"),
            category = SettingsCategory.AI_INTEGRATION,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.AI_INTEGRATION.id)
        ),

        // ─── Backup & Restore ───────────────────────────────────────────────────────
        SearchableSetting(
            title = "Export Backup",
            subtitle = "Save your app data to a file",
            keywords = listOf("export", "backup", "save", "restore"),
            category = SettingsCategory.BACKUP_RESTORE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.BACKUP_RESTORE.id)
        ),
        SearchableSetting(
            title = "Import Backup",
            subtitle = "Restore app data from a file",
            keywords = listOf("import", "restore", "backup", "recover"),
            category = SettingsCategory.BACKUP_RESTORE,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.BACKUP_RESTORE.id)
        ),

        // ─── Last.fm ────────────────────────────────────────────────────────────────
        SearchableSetting(
            title = "Last.fm Login",
            subtitle = "Connect your Last.fm account",
            keywords = listOf("lastfm", "last.fm", "login", "account"),
            category = SettingsCategory.LASTFM,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LASTFM.id)
        ),
        SearchableSetting(
            title = "Scrobbling",
            subtitle = "Auto-scrobble played tracks",
            keywords = listOf("scrobble", "lastfm", "track", "history"),
            category = SettingsCategory.LASTFM,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.LASTFM.id)
        ),

        // ─── Equalizer ──────────────────────────────────────────────────────────────
        SearchableSetting(
            title = "Equalizer",
            subtitle = "System audio effects",
            keywords = listOf("equalizer", "eq", "audio", "bass", "treble", "dolby"),
            category = SettingsCategory.EQUALIZER,
            route = Screen.SettingsCategory.createRoute(SettingsCategory.EQUALIZER.id)
        ),

        // ─── Device Capabilities ────────────────────────────────────────────────────
        SearchableSetting(
            title = "Device Capabilities",
            subtitle = "Hardware and audio capabilities",
            keywords = listOf("device", "capabilities", "hardware", "audio", "specs"),
            category = SettingsCategory.DEVICE_CAPABILITIES,
            route = Screen.DeviceCapabilities.route
        ),

        // ─── Developer / Experimental ───────────────────────────────────────────────
        SearchableSetting(
            title = "Experimental Features",
            subtitle = "Full player tweaks & more",
            keywords = listOf("experimental", "developer", "beta", "tweak", "advanced"),
            category = SettingsCategory.DEVELOPER,
            route = Screen.Experimental.route
        ),
        SearchableSetting(
            title = "Album Art Resolution",
            subtitle = "Quality of downloaded album art",
            keywords = listOf("album art", "resolution", "quality", "experimental"),
            category = SettingsCategory.DEVELOPER,
            route = Screen.Experimental.route
        ),

        // ─── About ──────────────────────────────────────────────────────────────────
        SearchableSetting(
            title = "About PixelMusic",
            subtitle = "App information and changelog",
            keywords = listOf("about", "version", "changelog", "info", "update"),
            category = SettingsCategory.ABOUT,
            route = Screen.About.route
        )
    )

    fun search(query: String): List<SearchableSetting> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return emptyList()
        return entries
            .asSequence()
            .map { entry -> entry to score(entry, q) }
            .filter { it.second > 0 }
            .sortedByDescending { it.second }
            .map { it.first }
            .toList()
    }

    /** Simple relevance: title match > subtitle > keywords */
    private fun score(entry: SearchableSetting, q: String): Int {
        var s = 0
        if (entry.title.lowercase().contains(q)) s += 100
        if (entry.subtitle.lowercase().contains(q)) s += 40
        entry.keywords.forEach { kw ->
            if (kw.lowercase().contains(q)) s += 20
        }
        return s
    }
}
