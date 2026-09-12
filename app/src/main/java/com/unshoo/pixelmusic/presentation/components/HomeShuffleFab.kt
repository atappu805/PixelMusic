package com.unshoo.pixelmusic.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.unshoo.pixelmusic.R
import kotlinx.coroutines.delay

@Composable
fun HomeShuffleFab(
    isShuffleEnabled: Boolean,
    isPlayerActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isExploreMode: Boolean = false,
    onLongClick: (() -> Unit)? = null,
    onSwipeUp: (() -> Unit)? = null,
) {
    val systemNavBarInset = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    val navState = when {
        systemNavBarInset > 35.dp -> 2
        systemNavBarInset > 5.dp -> 1
        else -> 0
    }

    val activeHeight = when (navState) {
        2 -> 106.dp
        1 -> 74.dp
        else -> 55.dp
    }
    val inactiveHeight = when (navState) {
        2 -> 50.dp
        1 -> 24.dp
        else -> 10.dp
    }

    var isPlayerActiveDelayed by remember { mutableStateOf(isPlayerActive) }

    LaunchedEffect(isPlayerActive) {
        if (isPlayerActive) {
            isPlayerActiveDelayed = true
        } else {
            delay(4000)
            isPlayerActiveDelayed = false
        }
    }

    val targetOffset = if (isPlayerActiveDelayed) activeHeight else inactiveHeight

    val animatedBottomOffset by animateDpAsState(
        targetValue = targetOffset,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "fabBottomOffset"
    )

    val dynamicHorizontalPadding = if (systemNavBarInset > 30.dp) 14.dp else systemNavBarInset
    val dynamicEndPadding = 16.dp + dynamicHorizontalPadding

    val containerColor = when {
        isExploreMode -> MaterialTheme.colorScheme.primary
        isShuffleEnabled -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val contentColor = when {
        isExploreMode -> MaterialTheme.colorScheme.onPrimary
        isShuffleEnabled -> MaterialTheme.colorScheme.onPrimary
        else -> MaterialTheme.colorScheme.onTertiaryContainer
    }

    // Swipe-up detection state
    var dragAccumulator by remember { mutableFloatStateOf(0f) }
    var swipeTriggered by remember { mutableStateOf(false) }
    val swipeThresholdPx = 60f

    Box(
        modifier = modifier
            .padding(bottom = animatedBottomOffset.coerceAtLeast(0.dp), end = dynamicEndPadding)
            .size(64.dp)
            .clip(CircleShape)
            .background(containerColor)
            .then(
                if (onSwipeUp != null) {
                    Modifier.pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onDragStart = {
                                dragAccumulator = 0f
                                swipeTriggered = false
                            },
                            onDragEnd = {
                                dragAccumulator = 0f
                                swipeTriggered = false
                            },
                            onDragCancel = {
                                dragAccumulator = 0f
                                swipeTriggered = false
                            },
                            onVerticalDrag = { change, dragAmount ->
                                change.consume()
                                dragAccumulator += dragAmount
                                if (!swipeTriggered && dragAccumulator < -swipeThresholdPx) {
                                    swipeTriggered = true
                                    onSwipeUp.invoke()
                                }
                            }
                        )
                    }
                } else Modifier
            )
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
                role = Role.Button
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isExploreMode) {
            Icon(
                imageVector = Icons.Rounded.AutoAwesome,
                contentDescription = "Smart Mix",
                tint = contentColor,
                modifier = Modifier.size(32.dp)
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.rounded_shuffle_24),
                contentDescription = stringResource(R.string.cd_shuffle_play),
                tint = contentColor,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
