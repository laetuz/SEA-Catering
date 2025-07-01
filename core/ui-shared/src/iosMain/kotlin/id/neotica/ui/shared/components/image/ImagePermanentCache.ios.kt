package id.neotica.ui.shared.components.image

import androidx.compose.ui.graphics.ImageBitmap
import io.ktor.http.ContentType.Application

actual object AppContext

actual suspend fun imageExists(fileName: String): Boolean {
    TODO("Not yet implemented")
}

actual fun loadImageFromDisk(fileName: String): ImageBitmap? {
    TODO("Not yet implemented")
}
actual suspend fun downloadAndCacheImage(url: String, fileName: String): ImageBitmap? {
    TODO("Not yet implemented")
}