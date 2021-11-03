package tmg.utilities.extensions

import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

@JvmOverloads
fun LocalTime.format(format: String, locale: Locale = Locale.ENGLISH): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(format, locale)
    return this.format(dateTimeFormatter)
}