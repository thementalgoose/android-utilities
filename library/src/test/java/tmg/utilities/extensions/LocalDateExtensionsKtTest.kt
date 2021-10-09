package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.threeten.bp.LocalDate

internal class LocalDateExtensionsKtTest {

    @Test
    fun `LocalDate format correctly`() {
        val input = LocalDate.of(2022, 1, 29)
        val expected = "29 January 2022"

        assertEquals(expected, input.format("dd MMMM yyyy"))
    }
}