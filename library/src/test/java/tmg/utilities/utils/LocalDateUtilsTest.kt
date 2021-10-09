package tmg.utilities.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeParseException

internal class LocalDateUtilsTest {

    @ParameterizedTest(name = "requireFromDate({0}) does now throw an exception")
    @CsvSource(
        "2020-1-1",
        "2020-01-1",
        "2020-01-01",
        "2020-January-1",
        "2020-Jan-01"
    )
    fun `requireFromDate does not throw exception for valid date parses`(date: String) {
        val expected = LocalDate.of(2020, 1, 1)
        assertEquals(expected, LocalDateUtils.requireFromDate(date))
    }

    @ParameterizedTest(name = "requireFromDate({0}) is invalid and throws an exception")
    @CsvSource(
        "2020-1-32",
        "1999-13-01",
        "2023-12-32",
        "something-random"
    )
    fun `requireFromDate throws exception for invalid date parses`(date: String) {
        assertThrows<DateTimeParseException> {
            LocalDateUtils.requireFromDate(date)
        }
    }

    @ParameterizedTest(name = "fromDate({0}) does now throw an exception")
    @CsvSource(
        "2020-1-1",
        "2020-01-1",
        "2020-01-01",
        "2020-January-1",
        "2020-Jan-01"
    )
    fun `fromDate does not throw exception for valid date parses`(date: String) {
        val expected = LocalDate.of(2020, 1, 1)
        assertEquals(expected, LocalDateUtils.fromDate(date))
    }

    @ParameterizedTest(name = "fromDate({0}) is invalid and throws an exception")
    @CsvSource(
        "2020-1-32",
        "1999-13-01",
        "2023-12-32",
        "something-random"
    )
    fun `fromDate returns null for invalid date`(date: String) {
        assertNull(LocalDateUtils.fromDate(date))
    }

    @Test
    fun `requireFromDate value maps correctly`() {
        val input = "1994-10-17"
        val expected = LocalDate.of(1994, 10, 17)

        assertEquals(expected, LocalDateUtils.requireFromDate(input))
    }

    @Test
    fun `isDateValid returns true for valid date`() {
        val input = "2023-01-1"
        assertTrue(LocalDateUtils.isDateValid(input))
    }

    @Test
    fun `isDateValid returns false for invalid date`() {
        val input = "2023-01-32"
        assertFalse(LocalDateUtils.isDateValid(input))
    }
}