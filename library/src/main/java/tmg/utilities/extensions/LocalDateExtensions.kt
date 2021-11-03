package tmg.utilities.extensions

import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

@JvmOverloads
fun LocalDate.format(format: String, locale: Locale = Locale.UK): String? {
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

