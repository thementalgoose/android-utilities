package tmg.utilities.extensions

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalField
import java.time.temporal.WeekFields
import java.util.*

@JvmOverloads
fun LocalDate.format(format: String, locale: Locale = Locale.ENGLISH): String? {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(format, locale)
    return this.format(dateTimeFormatter)
}

/**
 * Returns the years difference between a reference now date and the source date
 * @param now
 */
fun LocalDate.age(now: LocalDate = LocalDate.now()): Int {
    return Period.between(this, now).years
}


/**
 * Returns the LocalDate at the start of day
 * @param mondayIsStart If monday is the start of the week. Sunday otherwise
 */
@JvmOverloads
fun LocalDate.startOfWeek(mondayIsStart: Boolean = true): LocalDate {
    val fieldISO: TemporalField = WeekFields.of(if (mondayIsStart) Locale.UK else Locale.US).dayOfWeek()
    return this.with(fieldISO, 1)
}