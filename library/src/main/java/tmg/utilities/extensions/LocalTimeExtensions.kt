package tmg.utilities.extensions

import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

fun LocalTime.format(format: String): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(format)
    return this.format(dateTimeFormatter)
}