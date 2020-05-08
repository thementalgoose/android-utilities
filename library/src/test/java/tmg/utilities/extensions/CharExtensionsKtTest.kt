package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CharExtensionsKtTest {


    @ParameterizedTest
    @CsvSource(
        "A,4,AAAA",
        "2,1,2",
        "L,0,"
    )
    fun `CharExtensions toLength extends properly`(char: Char, length: Int, expected: String?) {

        assertEquals(expected ?: "", char.toLength(length))
    }

    @Test
    fun `CharExtensions toLength negative number will return empty string`() {

        assertEquals("", 'A'.toLength(-1))
    }
}