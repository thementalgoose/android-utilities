package tmg.utilities.extensions

import java.util.*

//region Date

fun Long.toDate(): Date {
    return Date(if (this <= 0L) 0L else this)
}

/**
 * Convert a Long to a date string
 * @param format Format for the date, defaults to "dd/MM/yyyy HH:mm"
 */
fun Long.toDateString(format: String = "dd/MM/yyyy HH:mm"): String {
    val date = Date(this)
    return date.toFormat(format)
}

//endregion