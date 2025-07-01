package id.neotica.ui.shared.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import id.neotica.ui.shared.theme.DarkPrimary
import id.neotica.ui.shared.theme.NegativePrimary

@Composable
fun NeoButton(
    text: String,
    modifier: Modifier = Modifier,
    maxWidth: Boolean = false,
    width: Dp = Dp.Unspecified,
    enabled: Boolean = true,
    negative: Boolean = false,
    onClick: () -> Unit = {},
) {
    val defaultModifier = Modifier.width(width)
    val maxWidthModifier = Modifier.fillMaxWidth()
    val buttonColors = if (negative) {
        ButtonDefaults.buttonColors(
            containerColor = NegativePrimary,
            contentColor = Color.White,
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = DarkPrimary,
            contentColor = Color.White,
        )
    }
    Button(
        modifier = modifier.then(if (maxWidth) maxWidthModifier else defaultModifier),
        onClick = {
            onClick()
        },
        enabled = enabled,
        colors = buttonColors,
        shape = RoundedCornerShape(20)
    ) {
        Text(text)
    }
}