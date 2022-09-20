package tmg.utilities.extensions

import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.TemporalField
import org.threeten.bp.temporal.WeekFields
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