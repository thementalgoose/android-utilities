package tmg.utilities.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeParseException
import tmg.utilities.extensions.toLocalDate
import tmg.utilities.utils.LocalDateUtils.Companion.daysBetween
import tmg.utilities.utils.LocalTimeUtils.Companion.fromTime
import tmg.utilities.utils.LocalTimeUtils.Companion.isTimeValid
import tmg.utilities.utils.LocalTimeUtils.Companion.requireFromTime

internal class LocalTimeUtilsTest {

    @ParameterizedTest(name = "requireFromTime({0}) does now throw an exception")
    @CsvSource(
        "14:00:00Z",
        "14:00:00+0100",
        "14:00:00",
        "14:00:00.000",
        "14:00"
    )
    fun `requireFromTime does not throw exception for valid time parses`(time: String) {
        val expected = LocalTime.of(14, 0, 0)
        assertEquals(expected, requireFromTime(time))
    }

    @ParameterizedTest(name = "requireFromTime({0}) is invalid and throws an exception")
    @CsvSource(
        "12",
        "12:30:82",
        "null",
        "something-random"
    )
    fun `requireFromTime throws exception for invalid date parses`(time: String) {
        assertThrows<DateTimeParseException> {
            requireFromTime(time)
        }
    }

    @ParameterizedTest(name = "fromTime({0}) does now throw an exception")
    @CsvSource(
        "14:00:00Z",
        "14:00:00+0100",
        "14:00:00",
        "14:00:00.000",
        "14:00"
    )
    fun `fromTime does not throw exception for valid date parses`(time: String) {
        val expected = LocalTime.of(14, 0, 0)
        assertEquals(expected, requireFromTime(time))
    }

    @ParameterizedTest(name = "fromTime({0}) is invalid and throws an exception")
    @CsvSource(
        "12",
        "12:30:82",
        "null",
        "something-random"
    )
    fun `fromTime returns null for invalid date`(time: String) {
        assertNull(fromTime(time))
    }

    @Test
    fun `requireFromTime value maps correctly`() {
        val input = "13:43:03"
        val expected = LocalTime.of(13, 43, 3)

        assertEquals(expected, requireFromTime(input))
    }

    @Test
    fun `isDateValid returns true for valid date`() {
        val input = "01:00:00"
        assertTrue(isTimeValid(input))
    }

    @Test
    fun `isDateValid returns false for invalid date`() {
        val input = "25:00:00"
        assertFalse(isTimeValid(input))
    }

    @ParameterizedTest(name = "days between {0} and {1} is calculated to be {2}")
    @CsvSource(
        "2020-12-05,2020-12-04,-1",
        "2020-12-05,2020-12-05,0",
        "2020-12-05,2020-12-06,1",
        "1995-12-05,2020-12-06,9133"
    )
    fun `daysBetween between two dates results in expected days`(start: String, end: String, days: Int) {
        val inputStart = start.toLocalDate()!!
        val inputEnd = end.toLocalDate()!!

        assertEquals(days, daysBetween(inputStart, inputEnd))
    }
}