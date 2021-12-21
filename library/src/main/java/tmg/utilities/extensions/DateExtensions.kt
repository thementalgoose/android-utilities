package tmg.utilities.extensions

import java.text.SimpleDateFormat
import java.util.*

//region String formatting

/**
 * Convert a Date to a string with a given format
 * @param dateFormat Defaults to `dd/MM/yyyy HH:mm`
 */
@Deprecated("These are unreliable methods of printing the date. Please use either LocalDate or Calendar")
fun Date.toFormat(dateFormat: String = "dd/MM/yyyy HH:mm", locale: Locale = Locale.getDefault()): String {
    val sdf: SimpleDateFormat = SimpleDateFormat(dateFormat, locale)
    return sdf.format(this)
}
@Deprecated("These are unreliable methods of printing the date. Please use either LocalDate or Calendar", level = DeprecationLevel.ERROR)
fun Date.toFormatHHMM(separator: String = ":", locale: Locale = Locale.getDefault()): String {
    return toFormat("HH${separator}mm", locale)
}
@Deprecated("These are unreliable methods of printing the date. Please use either LocalDate or Calendar", level = DeprecationLevel.ERROR)
fun Date.toFormatYYYYMMDD(separator: String = "-", locale: Locale = Locale.getDefault()): String {
    return toFormat("yyyy${separator}MM${separator}dd", locale)
}
@Deprecated("These are unreliable methods of printing the date. Please use either LocalDate or Calendar", level = DeprecationLevel.ERROR)
fun Date.toFormatDDMMYYYY(separator: String = "-", locale: Locale = Locale.getDefault()): String {
    return toFormat("dd${separator}MM${separator}yyyy", locale)
}
@Deprecated("These are unreliable methods of printing the date. Please use either LocalDate or Calendar", level = DeprecationLevel.ERROR)
fun Date.toFormatMMDDYYYY(separator: String = "-", locale: Locale = Locale.getDefault()): String {
    return toFormat("MM${separator}dd${separator}yyyy", locale)
}

/**
 * Get operations on a date object
 * Days of week are returned 1 Monday through 7 Sunday
 */
fun Date.dayOfWeek(): Int {
    return ((toCal().get(Calendar.DAY_OF_WEEK) + 5) % 7) + 1
}
fun Date.dayOfWeekString(isShort: Boolean = true): String {
    return toFormat(if (isShort) "E" else "EEEE").replace(".", "")
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
fun Date.monthString(isShort: Boolean): String {
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
    cal.firstDayOfWeek = Calendar.MONDAY
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

/**
 * Relative time
 */
fun Date.secondsDiff(secondDate: Date): Int {
    return ((secondDate.time - this.time) / 1000L).toInt()
}
fun Date.minutesDiff(secondDate: Date): Int {
    return secondsDiff(secondDate) / 60
}

//endregion