package tmg.utilities.extensions

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@JvmOverloads
fun LocalTime.format(format: String, locale: Locale = Locale.ENGLISH): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(format, locale)
    return this.format(dateTimeFormatter)
}