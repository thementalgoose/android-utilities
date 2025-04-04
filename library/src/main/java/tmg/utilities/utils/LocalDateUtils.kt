package tmg.utilities.utils

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit
import java.lang.Exception
import java.lang.RuntimeException
import java.util.*
import kotlin.jvm.Throws

class LocalDateUtils {
    companion object {
        @JvmStatic
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
        @JvmStatic
        fun requireFromDate(dateString: String, dateFormats: List<String> = defaultDateFormats, locale: Locale = Locale.ENGLISH): LocalDate {
            return dateFormats.firstNotNullOfOrNull { pattern ->
                try {
                    LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern, locale))
                } catch (e: RuntimeException) {
                    null
                }
            } ?: throw DateTimeParseException("Failed to parse date string $dateString with no supported patterns", "", 0)
        }

        @JvmOverloads
        @JvmStatic
        fun fromDate(dateString: String?, dateFormats: List<String> = defaultDateFormats, locale: Locale = Locale.ENGLISH): LocalDate? {
            if (dateString == null) {
                return null
            }
            return try {
                return requireFromDate(dateString, dateFormats, locale)
            } catch (e: Exception) {
                null
            }
        }

        @JvmOverloads
        @JvmStatic
        fun isDateValid(dateString: String?, dateFormats: List<String> = defaultDateFormats, locale: Locale = Locale.ENGLISH): Boolean {
            return fromDate(dateString, dateFormats, locale) != null
        }

        @JvmStatic
        fun daysBetween(start: LocalDate, end: LocalDate): Int {
            return when {
                start < end -> ChronoUnit.DAYS.between(start, end).toInt()
                start > end -> -ChronoUnit.DAYS.between(end, start).toInt()
                else -> 0
            }
        }
    }
}