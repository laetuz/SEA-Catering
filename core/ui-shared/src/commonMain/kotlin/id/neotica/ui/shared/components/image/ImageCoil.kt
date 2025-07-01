package id.neotica.ui.shared.components.image

//import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import id.neotica.ui.shared.components.LoadingIndicator
//import org.koin.core.context.KoinContext

//hey
@Composable
fun ImageCoil(
    url: String,
    size: Dp? = null,
    height: Dp? = null,
    width: Dp? = null,
    modifier: Modifier? = null,
    fillMaxWidth: Boolean = true,
    round: Boolean = false,
    scaleCustom: Boolean = false,
    contentScale: ContentScale = ContentScale.Fit,
    contentAlignment: Alignment = Alignment.Center,
    context: PlatformContext = LocalPlatformContext.current,
    refreshCache: Boolean = false,
    onLongClick: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {

    val maxWidth = if (fillMaxWidth) {
        Modifier.fillMaxWidth()
    } else {
        Modifier
    }

    Box(
        maxWidth,
        contentAlignment = contentAlignment
    ) {
        SubcomposeAsyncImage(
            model =
                ImageRequest.Builder(context)
                .memoryCachePolicy(if (refreshCache) CachePolicy.DISABLED else CachePolicy.ENABLED)
                .diskCachePolicy(if (refreshCache) CachePolicy.DISABLED else CachePolicy.ENABLED)
                .data(url)
                .build(),
            contentDescription =  "",
            modifier = Modifier
                .then(modifier ?: Modifier)
                .then(if (height != null) Modifier.height(height) else Modifier)
                .then(if (width != null) Modifier.width(width) else Modifier)
                .then(if (size != null) Modifier.size(size) else Modifier)
                .clip(RoundedCornerShape(if (round) 120.dp else 12.dp))
                .then( if (onClick != null) {
                    Modifier.combinedClickable(
                        onLongClick = onLongClick
                    ) { onClick() }
                } else Modifier),
            loading = {
                Box(
                    Modifier.padding(12.dp)
                ) { LoadingIndicator() }
            },
            contentScale = if(round) ContentScale.Crop else {
                if (!scaleCustom) {
                    ContentScale.Fit
                } else {
                    contentScale
                }
            }
        )
    }
}