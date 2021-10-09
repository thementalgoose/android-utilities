package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.threeten.bp.LocalTime

internal class LocalTimeExtensionsKtTest {

    @Test
    fun `LocalTime format correctly`() {
        val input = LocalTime.of(5, 6, 7)
        val expected = "05 06 7"

        assertEquals(expected, input.format("HH mm s"))
    }
}