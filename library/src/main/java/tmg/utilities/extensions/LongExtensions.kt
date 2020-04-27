package tmg.utilities.extensions

import java.util.*

//region Date

/**
 * Convert a long representing the milliseconds of a date to a date object
 */
fun Long.toDate(): Date? {
    return if (this < 0L) null else Date(this)
}

/**
 * Convert a Long to a date string
 * @param format Format for the date, defaults to "dd/MM/yyyy HH:mm"
 */
fun Long.toDateString(format: String = "dd/MM/yyyy HH:mm"): String? {
    return toDate()?.toFormat(format)
}

//endregion