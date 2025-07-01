package id.neotica.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.UUID
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

actual fun randomUUID(): String = UUID.randomUUID().toString()

@OptIn(ExperimentalTime::class)
actual fun convertLongToTime(
    time: Long,
    minutesOnly: Boolean,
    clean: Boolean,
    last: Boolean
): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
    format.timeZone = TimeZone.getDefault()

    if (last) {
        val now = Clock.System.now().toEpochMilliseconds() //getCurrentTimeMillis().time
        val diffInMillis = now - time
        val diffInMinutes = diffInMillis / (1000 * 60)
        val diffInHours = diffInMinutes / 60
        val diffInDays = diffInHours / 24

        return when {
            diffInMinutes < 60 -> {
                "$diffInMinutes minutes ago"
            }
            diffInHours < 24 -> {
                "$diffInHours hour${if (diffInHours > 1) "s" else ""} ago"
            }
            else -> {
                val fullDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                fullDateFormat.timeZone = TimeZone.getDefault()
                fullDateFormat.format(date)
            }
        }
    }
    if (clean) {
        val cleanFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        cleanFormat.timeZone = TimeZone.getDefault()
        return cleanFormat.format(date)
    }
    return if (minutesOnly) {
        val minutes = SimpleDateFormat("HH:mm", Locale.getDefault())
        minutes.timeZone = TimeZone.getDefault()
        minutes.format(date)
    } else {
        format.format(date)
    }
}