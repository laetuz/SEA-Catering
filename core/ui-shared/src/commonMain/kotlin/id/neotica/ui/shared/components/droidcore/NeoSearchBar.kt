package id.neotica.ui.shared.components.droidcore

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import id.neotica.rickpository.res.MR
import id.neotica.ui.shared.theme.FontSize
import id.neotica.ui.shared.theme.Transparent
import id.neotica.ui.shared.theme.TransparentText40

@Composable
fun NeoSearchBar(
    value: MutableState<String>,
    onValueChange: ((String) -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    modifier: Modifier? = null,
    imeAction: ImeAction = ImeAction.Search,
    imeSend: (() -> Unit)? = null,
    focusRequester: FocusRequester? = null,
    padding: PaddingValues = PaddingValues(),
    trailingIcon: (@Composable () -> Unit)? = null,
    label: String? = null
) {
    var textFieldValue by remember { value }
    val keyboardController = LocalSoftwareKeyboardController.current

    focusRequester?.let {
        DisposableEffect(Unit) {
            onDispose { keyboardController?.hide() }
        }
        LaunchedEffect(Unit) {
            it.requestFocus()
        }
    }

    Card(
        Modifier
            .then(modifier ?: Modifier)
            .padding(padding)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = TransparentText40,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors().copy(containerColor = Transparent),
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leadingIcon != null) {
                leadingIcon.invoke()
            } else {
                Icon(
                    painter = painterResource(MR.images.ic_search),
                    contentDescription = "",
                    tint = TransparentText40,
                    modifier = Modifier.padding(horizontal =  16.dp)
                )
            }
            BasicTextField(
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                    onValueChange?.invoke(it)
                },
                modifier = Modifier
                    .background(color = Transparent)
                    .fillMaxWidth()
                    .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier)
                    .weight(1f),
                textStyle = TextStyle.Default.copy(
                    fontSize = FontSize.Normal,
                    color = White,
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = imeAction
                ),
                keyboardActions = if (imeSend!= null) KeyboardActions(
                    onSend = {imeSend()}
                ) else KeyboardActions.Default,
                cursorBrush = SolidColor(White),
            ) { innerTextField ->

                Row(
                    Modifier
                        .height(46.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box {
                        innerTextField()
                        if (textFieldValue.isEmpty()) {
                            Text(
                                text = label ?: "",
                                modifier = Modifier,
                                color = TransparentText40,
                                fontSize = FontSize.Normal,
                                maxLines = 1,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }
            if (trailingIcon != null) {
                trailingIcon()
            }
        }
    }
}