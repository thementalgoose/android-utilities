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
        "1995-11-16,2020-11-15,25",
        "1995-11-16,2020-11-16,26",
        "1995-11-16,2020-11-17,26",
        "1990-11-01,2021-05-23,31",
    )
    fun `LocalDate age calculates age correctly`(source: String, now: String, expectedAge: Int) {

        val sourceLocalDate = source.toLocalDate()!!
        val nowLocalDate = now.toLocalDate()!!

        assertEquals(expectedAge, sourceLocalDate.age(nowLocalDate))
    }
}