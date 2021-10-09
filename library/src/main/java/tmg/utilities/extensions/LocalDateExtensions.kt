package tmg.utilities.extensions

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

fun LocalDate.format(format: String): String? {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(format)
    return this.format(dateTimeFormatter)
}