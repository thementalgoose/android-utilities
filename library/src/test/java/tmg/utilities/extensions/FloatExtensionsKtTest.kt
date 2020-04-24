package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class FloatExtensionsKtTest {

    @ParameterizedTest
    @CsvSource(
        "0.02113,3,0.021",
        "0.0259,3,0.026",
        "0.0250,3,0.025",
        "0.0253,3,0.025",
        "0.025501,3,0.026",
        "0.0255,1,0.0",
        "1.23412,4,1.2341"
    )
    fun `FloatExtensions toDecimalPlaces formats properly`(source: String, decimalPlaces: Int, expected: String) {

        assertEquals(expected.toFloat(), source.toFloat().toDecimalPlaces(decimalPlaces))
    }

    @ParameterizedTest
    @CsvSource(
        "-1.0f,0.0f",
        "0.0f,0.0f",
        "1.0f,1.0f"
    )
    fun `FloatExtensions positive any number makes it positive`(source: Float, expected: Float) {

        assertEquals(expected, source.positive())
    }
}