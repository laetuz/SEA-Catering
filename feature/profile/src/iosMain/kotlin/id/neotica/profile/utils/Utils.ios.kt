package id.neotica.profile.utils

import coil3.Uri
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSData
import platform.Foundation.NSFileManager
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSURL
import platform.Foundation.dataWithContentsOfURL
import platform.Foundation.writeToFile
import platform.UIKit.UIGraphicsBeginImageContext
import platform.UIKit.UIGraphicsEndImageContext
import platform.UIKit.UIGraphicsGetImageFromCurrentImageContext
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation

//@OptIn(ExperimentalForeignApi::class)
//actual fun compressUri(uri: Uri): Uri {
//    // 1. Convert the coil3.Uri to a Foundation.NSURL
//    val imageUrl = NSURL.URLWithString(uri.toString())
//    if (imageUrl == null) {
//        // Return original uri or handle error if the URL is invalid
//        return uri
//    }
//
//    // 2. Load the image data from the URL
//    val imageData = NSData.dataWithContentsOfURL(imageUrl)
//    if (imageData == null) {
//        // Return original uri or handle error if data can't be loaded
//        return uri
//    }
//
//    // 3. Create a UIImage from the data
//    val image = UIImage.imageWithData(imageData) ?: return uri
//
//    // 4. Compress the image
//    // The second parameter is compression quality (0.0 to 1.0)
//    // 0.3 corresponds to the 30 quality in your Android code
//    val compressedImageData = UIImageJPEGRepresentation(image, 0.3) ?: return uri
//
//    // 5. Save the compressed data to a temporary file
//    val tempDir = NSTemporaryDirectory()
//    val tempFileName = "compressed_${NSUUID().UUIDString()}.jpg"
//    val tempFilePath = tempDir + tempFileName
//
//    compressedImageData.writeToFile(tempFilePath, true)
//
//    // 6. Return a new Uri pointing to the compressed file
//    return Uri.parse("file://$tempFilePath")
//}