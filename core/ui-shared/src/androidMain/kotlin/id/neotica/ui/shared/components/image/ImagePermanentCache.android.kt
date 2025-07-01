package id.neotica.ui.shared.components.image

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import java.io.File
import kotlin.Exception
import android.app.Application
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.asImageBitmap
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.toBitmap
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.FileOutputStream

actual object AppContext {
    private lateinit var application: Application

    fun setUp(context: Context) {
        application = context as Application
    }

    fun get(): Context {
        if (::application.isInitialized.not()) throw Exception("Application context isn't initialized")
        return application.applicationContext
    }
}

actual suspend fun imageExists(fileName: String): Boolean {
    return File(AppContext.get().filesDir, fileName).exists()
}

actual fun loadImageFromDisk(fileName: String): ImageBitmap? {
    val file = File(AppContext.get().filesDir, fileName)
    return if (file.exists()) {
        BitmapFactory.decodeFile(file.absolutePath)?.asImageBitmap()
    } else null
}
actual suspend fun downloadAndCacheImage(url: String, fileName: String): ImageBitmap? =
    suspendCancellableCoroutine { cont ->
        val imageLoader = ImageLoader(AppContext.get())
        val request = ImageRequest.Builder(AppContext.get())
            .data(url)
            .target { drawable ->
                val bitmap = (drawable as? BitmapDrawable)?.bitmap ?: drawable.toBitmap()
                saveBitmapToFile(AppContext.get(), bitmap, fileName)
            }
            .build()

        imageLoader.enqueue(request)
    }

private fun saveBitmapToFile(context: Context, bitmap: android.graphics.Bitmap, fileName: String) {
    val file = File(context.filesDir, fileName) // Create a file in filesDir with the specified file name
    var fileOutputStream: FileOutputStream? = null

    try {
        fileOutputStream = FileOutputStream(file)
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fileOutputStream) // Save as PNG with 100% quality
        fileOutputStream.flush()
        println("Image saved to: ${file.absolutePath}")
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        fileOutputStream?.close()
    }
}