package tmg.utilities.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@JvmOverloads
fun LocalDateTime.format(format: String, locale: Locale = Locale.ENGLISH): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(format, locale)
    return this.format(dateTimeFormatter)
}