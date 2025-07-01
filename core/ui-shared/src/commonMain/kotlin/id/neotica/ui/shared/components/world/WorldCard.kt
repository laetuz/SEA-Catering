package id.neotica.ui.shared.components.world

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import id.neotica.ui.shared.components.image.ImagePermanentCache
import id.neotica.ui.shared.components.image.loadImageFromDisk

@Composable
fun WorldCard(
    title: String,
    fileName: String,
    url: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable {
            onClick()
        }
    ) {
        Box {
            ImagePermanentCache(
                url = url,
                imageBitmap = loadImageFromDisk(fileName),
            )
            Text(
                text = title,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}