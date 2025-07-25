package id.neotica.ui.shared.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.abs

@Composable
fun FlipCard(
    frontSide: @Composable () -> Unit,
    backSide: @Composable () -> Unit
) {

    // This is our custom value of Axis-Y on coordinate system.
    var axisY by remember { mutableStateOf(0f) }

    // Manage animations.
    var isAutomaticAnimationActive by remember { mutableStateOf(true) }
    var isCompletingAnimationActive by remember { mutableStateOf(false) }
    var isQuickDragAnimationActive by remember { mutableStateOf(false) }

    // Follow drag amount to manage QuickDragAnimation.
    var animationDragAmount by remember { mutableStateOf(0f) }

    FlippingComposableHolder (
        frontSide = {
            frontSide()
//            Text("helloooo")
//            CardFrontSide(user = User())
        },
        backSide = {
            backSide()
        },
        positionAxisY = if (isAutomaticAnimationActive) {
            val automaticTurningAnimation = remember { Animatable(axisY) } // Auto-turning animation.

            LaunchedEffect(isAutomaticAnimationActive) {
                if (isAutomaticAnimationActive) {
                    automaticTurningAnimation.animateTo(
                        targetValue = if (axisY >= 0) {
                            axisY + 360f // Turn right.
                        } else {
                            - 360f + axisY // Turn left.
                        },
                        animationSpec = infiniteRepeatable(
                            tween(7000, easing = FastOutSlowInEasing)
                        ),
                    )
                }
            }
            axisY = automaticTurningAnimation.value // Do not forget to update axis-Y.
            automaticTurningAnimation.value // Finally, return animation value.
        }
        else if (isCompletingAnimationActive) {
            val completeTurningAnimation = remember { Animatable(axisY) }

            LaunchedEffect(isCompletingAnimationActive) {
                if (isCompletingAnimationActive) {
                    completeTurningAnimation.animateTo(
                        targetValue = if(abs(axisY.toInt()) % 360 <= 90) {
                            0f
                        }
                        else if (abs(axisY.toInt()) % 360 in 91..270) {
                            if (abs(axisY.toInt()) % 360 <= 270f) {

                                if (axisY > 0) 180f else -180f
                            }
                            else {
                                if (axisY > 0) 360f else -360f
                            }
                        }
                        else {
                            if (axisY > 0) 360f else -360f
                        },
                        animationSpec = tween(500, easing = FastOutLinearInEasing)
                    ).endState
                }
            }
            axisY = completeTurningAnimation.value
            completeTurningAnimation.value
        }
        else if (isQuickDragAnimationActive) {
            val completeQuickDragAnimation = remember { Animatable(axisY) }

            LaunchedEffect(isQuickDragAnimationActive) {
                if (isQuickDragAnimationActive) {

                    val completeTurningAnimationState = completeQuickDragAnimation.animateTo(
                        targetValue = if (animationDragAmount > 0) {
                            360f * 2
                        } else {
                            -360f * 2
                        },
                        animationSpec = tween(1250, easing = LinearEasing)
                    ).endState

                    if (!completeTurningAnimationState.isRunning) {
                        isQuickDragAnimationActive = false
                        isAutomaticAnimationActive = true
                    }
                }
            }
            axisY = completeQuickDragAnimation.value
            completeQuickDragAnimation.value
        }
        else {
            axisY
        },
        modifier = Modifier
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { offset ->
                        isAutomaticAnimationActive = false // Stop animation.
                        isCompletingAnimationActive = false // Stop animation.
                    },
                    onDragEnd = {

                        // Stop animations.
                        isAutomaticAnimationActive = false
                        isCompletingAnimationActive = false
                        isQuickDragAnimationActive = false

                        // If user did not drag enough, just show completing animation.
                        if (abs(animationDragAmount) > 12f) {
                            isQuickDragAnimationActive = true
                        } else {
                            isCompletingAnimationActive = true
                        }
                    },
                    onDragCancel = {

                    },
                    onHorizontalDrag = { change, dragAmount ->

                        // Decide to turn card in which side.
                        axisY = if (dragAmount < 0) {
                            (axisY - abs(dragAmount)) % 360 // Turn left for negative numbers.
                        } else {
                            (axisY + abs(dragAmount)) % 360 // Turn right for positive numbers.
                        }

                        animationDragAmount = dragAmount // Keep updated drag amount.
                    }
                )
            },
    )
}

/**
 * This holder manages logic to show correct side of the card.
 */
@Composable
fun FlippingComposableHolder(
    modifier: Modifier = Modifier,
    positionAxisY: Float,
    frontSide: @Composable () -> Unit = {},
    backSide: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                rotationY = positionAxisY // Move card according to value of customY.
                cameraDistance = 14f * density
            },
    ) {

        if (abs(positionAxisY.toInt()) % 360 <= 90) {
            Box {
                frontSide()
            }
        } else if (abs(positionAxisY.toInt()) % 360 in 91..270) {
            Column(
                modifier = Modifier
                    .graphicsLayer {
                        rotationY = 180f // Important to avoid mirror effect.
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
//                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) { backSide() }

            }
        } else {
            Box {
                frontSide()
            }
        }
    }
}
