package tmg.utilities.utils

import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import java.lang.Exception
import java.lang.RuntimeException
import java.util.*
import kotlin.jvm.Throws

class LocalTimeUtils {
    companion object {
        @JvmStatic
        val defaultTimeFormats = listOf(
            "HH:mm:ss'Z'",
            "HH:mm:ssZ",
            "HH:mm:ss",
            "HH:mm:ss.SSS",
            "HH:mm"
        )

        @Throws(DateTimeParseException::class)
        @JvmOverloads
        @JvmStatic
        fun requireFromTime(timeString: String, timeFormats: List<String> = defaultTimeFormats, locale: Locale = Locale.ENGLISH): LocalTime {
            return timeFormats.firstNotNullOfOrNull { pattern ->
                try {
                    LocalTime.parse(timeString, DateTimeFormatter.ofPattern(pattern, locale))
                } catch (e: RuntimeException) {
                    null
                }
            } ?: throw DateTimeParseException("Failed to parse time string $timeString with no supported patterns.", "", 0)
        }

        @JvmOverloads
        @JvmStatic
        fun fromTime(timeString: String?, timeFormats: List<String> = defaultTimeFormats, locale: Locale = Locale.ENGLISH): LocalTime? {
            if (timeString == null) {
                return null
            }
            return try {
                return requireFromTime(timeString, timeFormats, locale)
            } catch (e: Exception) {
                null
            }
        }

        @JvmOverloads
        @JvmStatic
        fun isTimeValid(timeString: String?, timeFormats: List<String> = defaultTimeFormats, locale: Locale = Locale.ENGLISH): Boolean {
            return fromTime(timeString, timeFormats, locale) != null
        }
    }
}