package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.threeten.bp.LocalDate

internal class LocalDateExtensionsKtTest {

    @Test
    fun `LocalDate format correctly`() {
        val input = LocalDate.of(2022, 1, 29)
        val expected = "29 January 2022"

        assertEquals(expected, input.format("dd MMMM yyyy"))
    }

    @ParameterizedTest(name = "Age between {0} and {1} is {2}")
    @CsvSource(
        "1995-11-16,2020-11-15,24",
        "1995-11-16,2020-11-16,25",
        "1995-11-16,2020-11-17,25",
        "1990-11-01,2021-05-23,30",
    )
    fun `LocalDate age calculates age correctly`(source: String, now: String, expectedAge: Int) {

        val sourceLocalDate = source.toLocalDate()!!
        val nowLocalDate = now.toLocalDate()!!

        assertEquals(expectedAge, sourceLocalDate.age(nowLocalDate))
    }

    @ParameterizedTest(name = "Given date {0}, startOfWeek monday returns {1}")
    @CsvSource(
        "2022-09-17,2022-09-12",
        "2022-09-18,2022-09-12",
        "2022-09-19,2022-09-19",
        "2022-09-20,2022-09-19",
        "2022-09-21,2022-09-19",
        "2022-09-22,2022-09-19",
        "2022-09-23,2022-09-19",
        "2022-09-24,2022-09-19",
        "2022-09-25,2022-09-19",
        "2022-09-26,2022-09-26"
    )
    fun `LocalDate startOfWeek isMonday true returns correctly`(initialDate: String, expectedDate: String) {
        val expected = expectedDate.toLocalDate("yyyy-MM-dd")!!
        val initial = initialDate.toLocalDate("yyyy-MM-dd")!!

        assertEquals(expected, initial.startOfWeek())
    }

    @ParameterizedTest(name = "Given date {0}, startOfWeek sunday returns {1}")
    @CsvSource(
        "2022-09-17,2022-09-11",
        "2022-09-18,2022-09-18",
        "2022-09-19,2022-09-18",
        "2022-09-20,2022-09-18",
        "2022-09-21,2022-09-18",
        "2022-09-22,2022-09-18",
        "2022-09-23,2022-09-18",
        "2022-09-24,2022-09-18",
        "2022-09-25,2022-09-25",
        "2022-09-26,2022-09-25"
    )
    fun `LocalDate startOfWeek isMonday false returns correctly`(initialDate: String, expectedDate: String) {
        val expected = expectedDate.toLocalDate("yyyy-MM-dd")!!
        val initial = initialDate.toLocalDate("yyyy-MM-dd")!!

        assertEquals(expected, initial.startOfWeek(mondayIsStart = false))
    }
}