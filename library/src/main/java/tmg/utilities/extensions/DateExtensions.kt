package tmg.utilities.extensions

import java.text.SimpleDateFormat
import java.util.*

//region String formatting

/**
 * Convert a Date to a format
 */
fun Date.toFormat(dateFormat: String): String {
    val sdf: SimpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
    return sdf.format(this)
}

/**
 * Shortcut conversion methodsfor
 */
fun Date.toFormatHHMM(separator: String = ":"): String {
    return toFormat("HH${separator}mm")
}
fun Date.toFormatYYYYMMDD(separator: String = "-"): String {
    return toFormat("yyyy${separator}MM${separator}dd")
}
fun Date.toFormatDDMMYYYY(separator: String = "-"): String {
    return toFormat("dd${separator}MM${separator}yyyy")
}

/**
 * Get operations on a date object
 */
fun Date.dayOfWeek(): Int {
    return (toCal().get(Calendar.DAY_OF_WEEK) + 6) % 7
}
fun Date.dayOfWeek(isShort: Boolean = true): String {
    return toFormat(if (isShort) "E" else "EEEE")
}
fun Date.dayOfMonth(): Int {
    return toCal().get(Calendar.DAY_OF_MONTH)
}
fun Date.year(): Int {
    return toCal().get(Calendar.YEAR)
}

/**
 * Month
 * @return The current month (1 - 12)
 */
fun Date.month(): Int {
    return toCal().get(Calendar.MONTH) + 1
}
fun Date.month(isShort: Boolean): String {
    return if (isShort) {
        toFormat("MMM")
    }
    else {
        toFormat("MMMM")
    }
}

/**
 * Weekday checks
 */
fun Date.isMonday(): Boolean {
    return toCal().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
}
fun Date.isTuesday(): Boolean {
    return toCal().get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY
}
fun Date.isWednesday(): Boolean {
    return toCal().get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY
}
fun Date.isThursday(): Boolean {
    return toCal().get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY
}
fun Date.isFriday(): Boolean {
    return toCal().get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
}
fun Date.isSaturday(): Boolean {
    return toCal().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
}
fun Date.isSunday(): Boolean {
    return toCal().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
}

/**
 * Start of week
 */
fun Date.startOfDay(): Date {
    val cal = toCal()
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    return cal.time
}
fun Date.startOfWeek(): Date {
    val cal = toCal()
    cal.set(Calendar.DAY_OF_WEEK, cal.firstDayOfWeek)
    return cal.time.startOfDay()
}
fun Date.startOfMonth(): Date {
    val cal = toCal()
    cal.set(Calendar.DAY_OF_MONTH, 1)
    return cal.time.startOfDay()
}

/**
 * Calendar manipulation methods
 */
fun Date.toCalendar(): Calendar {
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}
fun Date.toCal(): Calendar {
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}

//endregion