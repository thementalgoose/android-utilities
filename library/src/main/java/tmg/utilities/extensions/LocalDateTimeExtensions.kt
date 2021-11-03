package tmg.utilities.extensions

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

@JvmOverloads
fun LocalDateTime.format(format: String, locale: Locale = Locale.UK): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(format, locale)
    return this.format(dateTimeFormatter)
}