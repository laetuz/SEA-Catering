package id.neotica.ui.shared.components.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SlideInto(
    trigger: Boolean = false,
    inTime: Int = 500,
    outTime: Int = 500,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        trigger,
        enter = slideInVertically(
            initialOffsetY = {-30},
            animationSpec = tween(inTime, easing = LinearEasing)
        ),
        exit = slideOutVertically(
            targetOffsetY = {-30},
            animationSpec = tween(outTime, easing = LinearEasing)
        )
    ) {
        content()
    }
}