package tmg.utilities.utils

import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.jvm.Throws

object LocalTimeUtils {

    val defaultTimeFormats = listOf(
        "HH:mm:ss'Z'",
        "HH:mm:ssZ",
        "HH:mm:ss",
        "HH:mm:ss.SSS",
        "HH:mm"
    )

    @Throws(DateTimeParseException::class)
    @JvmOverloads
    fun requireFromTime(timeString: String, timeFormats: List<String> = defaultTimeFormats): LocalTime {
        return timeFormats
            .mapNotNull { pattern ->
                try {
                    LocalTime.parse(timeString, DateTimeFormatter.ofPattern(pattern))
                } catch (e: RuntimeException) {
                    null
                }
            }
            .firstOrNull() ?: throw DateTimeParseException("Failed to parse time string $timeString with no supported patterns.", "", 0)
    }

    @JvmOverloads
    fun fromTime(timeString: String?, timeFormats: List<String> = defaultTimeFormats): LocalTime? {
        if (timeString == null) {
            return null
        }
        return try {
            return requireFromTime(timeString, timeFormats)
        } catch (e: Exception) {
            null
        }
    }

    @JvmOverloads
    fun isTimeValid(timeString: String?, timeFormats: List<String> = defaultTimeFormats): Boolean {
        return fromTime(timeString, timeFormats) != null
    }
}