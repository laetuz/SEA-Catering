package id.neotica.domain.utils

expect fun randomUUID(): String

expect fun convertLongToTime(
    time: Long,
    minutesOnly: Boolean = false,
    clean: Boolean = false,
    last: Boolean = false
): String

