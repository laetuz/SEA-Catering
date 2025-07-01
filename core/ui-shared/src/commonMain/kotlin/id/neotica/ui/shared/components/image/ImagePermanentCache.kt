package id.neotica.ui.shared.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

//@Composable
//fun ImagePermanentCache(
//    modifier: Modifier = Modifier,
//    imageBitmap: ImageBitmap,
//    fileName: String,
//    url: String,
//    width: Dp? = null,
//    height: Dp? = null,
//    size: Dp ? = null,
//    contentScale: ContentScale = ContentScale.Fit,
//    clickable: (() -> Unit)? = null
//) {
//    if (ifFileExist(context, fileName)) {
//        Box(
//            Modifier
//                .then(if (width != null) Modifier.width(width) else Modifier)
//                .then(if (height != null) Modifier.height(height) else Modifier)
//                .then(if (size != null) Modifier.size(size) else Modifier)
//                .then(if (clickable != null) Modifier.clickable { clickable() } else Modifier),
//            contentAlignment = Alignment.Center
//        ) {
//            DisplaySavedImage(
//                fileName, contentScale,
//                modifier = modifier
//                    .then(if (width != null) Modifier.width(width) else Modifier)
//                    .then(if (height != null) Modifier.height(height) else Modifier)
//                    .then(if (size != null) Modifier.size(size) else Modifier)
//            )
//        }
//    } else {
//        downloadAndSaveImage(
//            url = url,
//            fileName = fileName,
//        )
//        if (clickable != null) {
//            ImageCoil(
//                url = url,
//                width = width,
//                height = height,
//                size = size,
//                contentScale = contentScale,
//                fillMaxWidth = false,
//            ) {
//                clickable()
//            }
//        } else {
//            ImageCoil(
//                url = url,
//                width = width,
//                height = height,
//                size = size,
//                contentScale = contentScale,
//                fillMaxWidth = false,
//            )
//        }
//    }
//}

@Composable
fun ImagePermanentCache(
    modifier: Modifier = Modifier,
    imageBitmap: ImageBitmap?,
    url: String,
    width: Dp? = null,
    height: Dp? = null,
    size: Dp? = null,
    contentScale: ContentScale = ContentScale.Fit,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .then(width?.let { Modifier.width(it) } ?: Modifier)
            .then(height?.let { Modifier.height(it) } ?: Modifier)
            .then(size?.let { Modifier.size(it) } ?: Modifier)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        contentAlignment = Alignment.Center
    ) {
        if (imageBitmap != null) {
            Image(
                bitmap = imageBitmap,
                contentDescription = "Cached Image",
                contentScale = contentScale,
                modifier = Modifier.matchParentSize()
            )
        } else {
            ImageCoil(
                url = url,
                width = width,
                height = height,
                size = size,
                contentScale = contentScale,
                fillMaxWidth = false,
                onClick = onClick
            )
        }
    }
}

expect object AppContext
expect suspend fun imageExists(fileName: String): Boolean
expect fun loadImageFromDisk(fileName: String): ImageBitmap?
expect suspend fun downloadAndCacheImage(url: String, fileName: String): ImageBitmap?

//@Composable
//private fun DisplaySavedImage(
//    fileName: String,
//    contentScale: ContentScale = ContentScale.Fit,
//    modifier: Modifier = Modifier,
//) {
//    val context = LocalPlatformContext.current
//    val bitmap = remember { loadImageFromFilesDir(context, fileName) }
//
//    bitmap?.let {
//        Image(
//            bitmap = it.asImageBitmap(),
//            contentScale = contentScale,
//            contentDescription = "Saved Image",
//            modifier = modifier
//        )
//    }
//}

//private fun loadImageFromFilesDir(context: Context, fileName: String): Bitmap? {
//    val file = File(context.filesDir, fileName)
//    return if (file.exists()) {
//        BitmapFactory.decodeFile(file.absolutePath)
//    } else {
//        null
//    }
//}

//private fun ifFileExist(context: Context, fileName: String): Boolean {
//    val file = File(context.filesDir, fileName)
//    return file.exists()
//}
//
//private fun downloadAndSaveImage(url: String, fileName: String) {
//    downloadAndCacheImage(url, fileName)
//    val imageLoader = Coil.imageLoader(context)
//    val request = ImageRequest.Builder(context)
//        .data(url)
//        .target { result ->
//            val bitmap = (result as? BitmapDrawable)?.bitmap ?: result.toBitmap()
//            saveBitmapToFile(context, bitmap, fileName)
//        }
//        .build()
//    imageLoader.enqueue(request)
//}
//
//private fun saveBitmapToFile(context: Context, bitmap: Bitmap, fileName: String) {
//    val file = File(context.filesDir, fileName) // Create a file in filesDir with the specified file name
//    var fileOutputStream: FileOutputStream? = null
//
//    try {
//        fileOutputStream = FileOutputStream(file)
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream) // Save as PNG with 100% quality
//        fileOutputStream.flush()
//        println("Image saved to: ${file.absolutePath}")
//    } catch (e: Exception) {
//        e.printStackTrace()
//    } finally {
//        fileOutputStream?.close()
//    }
//}