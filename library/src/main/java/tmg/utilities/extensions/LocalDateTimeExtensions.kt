package tmg.utilities.extensions

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

fun LocalDateTime.format(format: String): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(format)
    return this.format(dateTimeFormatter)
}