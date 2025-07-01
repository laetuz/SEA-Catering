package id.neotica.ui.shared.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import id.neotica.rickpository.res.MR
import id.neotica.ui.shared.components.image.ImageCoil
import id.neotica.ui.shared.theme.DarkPrimaryTransparent
import id.neotica.ui.shared.theme.DarkPrimaryTransparent80
import id.neotica.ui.shared.theme.TransparentText40

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileCard(
    name: String,
    image: String,
    size: Dp = 80.dp,
    paddingValues: PaddingValues = PaddingValues(),
    content: @Composable () -> Unit = {},
    alertTitle: String = "",
    alertButton: String = "",
    onLongClick: (() -> Unit) ?= null,
    onClick: () -> Unit = {}
) {
    val openDialog = remember { mutableStateOf(false) }

    val cardColorsPrime = CardColors(
        containerColor = DarkPrimaryTransparent80,
        contentColor = White,
        disabledContentColor = DarkPrimaryTransparent,
        disabledContainerColor = DarkPrimaryTransparent
    )

    if (openDialog.value) {
        // TODO: migrate droidcore to compose multiplatform
//        NeoAlert(
//            openDialog = openDialog,
//            title = alertTitle,
//            backButton = "Cancel",
//            confirmButton = {
//                NeoButton(
//                    alertButton,
//                    negative = true
//                ) {
//                    openDialog.value = false
//                }
//            }
//        )
    }

    Card(
        colors = cardColorsPrime,
        modifier = Modifier
            .combinedClickable(
                onClick = { onClick() },
                onLongClick = { onLongClick?.invoke() },
            )
            .padding(paddingValues),
    ) {
        Box {
            Column(
                Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                ImageCoil(
                    modifier = Modifier.padding(vertical = 8.dp),
                    url = image,
                    round = true,
                    size = size
                )
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                content()
            }
            IconButton (
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(0.dp),
                onClick = {
                    onLongClick?.invoke()
                },
            ) {
                Icon(
                    painter = painterResource(MR.images.three_dots),
                    contentDescription = "",
                    tint = TransparentText40
                )
            }
        }
    }
}