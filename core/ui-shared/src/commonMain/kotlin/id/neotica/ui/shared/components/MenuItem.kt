package id.neotica.ui.shared.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import id.neotica.droidcore.component.alert.NeoToast
import id.neotica.rickpository.res.MR

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuItem(
    title: String,
    textColor: Color = Unspecified,
    leadingIcon: Painter? = null,
    iconTint: Color = LocalContentColor.current,
    onClick: () -> Unit = {},
) {
    var toast = remember { mutableStateOf(false) }
    var toastMessage = remember { mutableStateOf("") }
    val haptic = LocalHapticFeedback.current

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true, color = White),
                onClick = onClick,
                onLongClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    toastMessage.value = title
                    toast.value = true
                },
            )
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (leadingIcon != null) {
            Icon(
                painter = leadingIcon,
                contentDescription = "Change Profile Picture",
                modifier = Modifier.padding(end = 8.dp),
                tint = iconTint
            )
        }
        Text(
            text = title,
            color = textColor,
            modifier = Modifier.weight(1f)
        )
        Icon(
            modifier = Modifier,
            painter = painterResource(MR.images.ic_forward),
            contentDescription = "",
            tint = White
        )
    }
    if (toast.value) NeoToast(toastMessage.value .toString(), toast)
}