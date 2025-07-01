package id.neotica.domain.utils
import platform.Foundation.NSUUID
import platform.Foundation.*
import kotlin.math.floor
import id.neotica.neoverse.getCurrentTimeMillis

actual fun randomUUID(): String = NSUUID.UUID().UUIDString()

actual fun convertLongToTime(
    time: Long,
    minutesOnly: Boolean,
    clean: Boolean,
    last: Boolean
): String {
    val date = NSDate.dateWithTimeIntervalSince1970(time.toDouble() / 1000)

    fun formatWithPattern(pattern: String): String {
        val formatter = NSDateFormatter()
        formatter.dateFormat = pattern
        formatter.locale = NSLocale.currentLocale
        formatter.timeZone = NSTimeZone.localTimeZone
        return formatter.stringFromDate(date)
    }

    if (last) {
        val now = NSDate.dateWithTimeIntervalSince1970(getCurrentTimeMillis().time.toDouble() / 1000)
        val diffInSeconds = now.timeIntervalSinceDate(date)
        val diffInMinutes = floor(diffInSeconds / 60).toInt()
        val diffInHours = floor(diffInMinutes / 60.0).toInt()
        val diffInDays = floor(diffInHours / 24.0).toInt()

        return when {
            diffInMinutes < 60 -> "$diffInMinutes minutes ago"
            diffInHours < 24 -> "$diffInHours hour${if (diffInHours > 1) "s" else ""} ago"
            else -> formatWithPattern("dd MMMM yyyy")
        }
    }

    if (clean) {
        return formatWithPattern("dd MMMM yyyy")
    }

    return if (minutesOnly) {
        formatWithPattern("HH:mm")
    } else {
        formatWithPattern("yyyy.MM.dd HH:mm")
    }
}