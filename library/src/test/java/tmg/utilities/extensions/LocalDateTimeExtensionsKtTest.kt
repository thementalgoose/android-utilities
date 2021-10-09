package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.threeten.bp.LocalDateTime

internal class LocalDateTimeExtensionsKtTest {

    @Test
    fun `LocalDateTime format correctly`() {
        val input = LocalDateTime.of(2022, 1, 29, 1, 1)
        val expected = "29 Jan 2022 01:01"

        assertEquals(expected, input.format("dd MMM yyyy HH:mm"))
    }
}