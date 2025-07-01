package id.neotica.ui.shared.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import id.neotica.ui.shared.components.image.ImageCoil
import id.neotica.ui.shared.theme.DarkPrimaryTransparent
import id.neotica.ui.shared.theme.Transparent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatListItem(
    image: String,
    content: @Composable () -> Unit = {},
    enableLongClick: Boolean = false,
    onLongClick: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    val openDialog = remember { mutableStateOf(false) }

    val cardColorsPrime = CardColors(
        containerColor = DarkPrimaryTransparent,
        contentColor = Color.White,
        disabledContentColor = DarkPrimaryTransparent,
        disabledContainerColor = DarkPrimaryTransparent
    )

    val cardColorsTransparent = CardColors(
        containerColor = Transparent,
        contentColor = Color.White,
        disabledContentColor = Transparent,
        disabledContainerColor = Transparent
    )

    val longClick: (() -> Unit)? = if (enableLongClick) {
        { openDialog.value = true }
    } else { null }

    val haptic = LocalHapticFeedback.current

    Card(
        colors = cardColorsTransparent,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true),
                onClick = { onClick() },
                onLongClick = {
                    onLongClick()
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    longClick?.invoke()
                },
             ),
    ) {
        Row(
            Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
        ) {
            Box(
                Modifier
                    .weight(1f)
            ) {
                val context = LocalPlatformContext.current
                ImageCoil(
                    url = image,
                    fillMaxWidth = false,
                    round = true,
                    size = 62.dp,
                    contentAlignment = Alignment.CenterStart
                )
            }
            Column(
                Modifier
                    .weight(4f)
            ) {
                content()
            }
        }
    }
}