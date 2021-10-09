package tmg.utilities.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.jvm.Throws

object LocalDateUtils {

    val defaultDateFormats = listOf(
        "yyyy-M-d",
        "yyyy-MM-dd",
        "yyyy-MM-d",
        "yyyy-MMM-dd",
        "yyyy-MMM-d",
        "yyyy-MMMM-d",
        "yyyy-MMMM-dd",
    )

    @Throws(DateTimeParseException::class)
    @JvmOverloads
    fun requireFromDate(dateString: String, dateFormats: List<String> = LocalDateUtils.defaultDateFormats): LocalDate {
        return dateFormats
            .mapNotNull { pattern ->
                try {
                    LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern))
                } catch (e: RuntimeException) {
                    null
                }
            }
            .firstOrNull() ?: throw DateTimeParseException("Failed to parse date string $dateString with no supported patterns", "", 0)
    }

    @JvmOverloads
    fun fromDate(dateString: String?, dateFormats: List<String> = LocalDateUtils.defaultDateFormats): LocalDate? {
        if (dateString == null) {
            return null
        }
        return try {
            return requireFromDate(dateString, dateFormats)
        } catch (e: Exception) {
            null
        }
    }

    @JvmOverloads
    fun isDateValid(dateString: String?, dateFormats: List<String> = LocalDateUtils.defaultDateFormats): Boolean {
        return fromDate(dateString, dateFormats) != null
    }
}