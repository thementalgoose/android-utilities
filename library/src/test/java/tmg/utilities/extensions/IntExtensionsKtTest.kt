package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class IntExtensionsKtTest {

    @ParameterizedTest
    @CsvSource(
        "12,0,24,12",
        "0,0,24,0",
        "24,0,24,24",
        "-8,-5,24,-5",
        "72,0,24,24"
    )
    fun `IntExtensions capTo checking integers are capped too`(source: Int, min: Int, max: Int, expected: Int) {

        assertEquals(expected, source.capTo(min, max))
    }

    @ParameterizedTest
    @CsvSource(
        "-1,0",
        "0,0",
        "1,1"
    )
    fun `IntExtensions positive making a number positive`(number: Int, expected: Int) {

        assertEquals(expected, number.positive())
    }

    @ParameterizedTest
    @CsvSource(
        "-1,-1",
        "0,0",
        "1,0"
    )
    fun `IntExtensions negative making a number positive`(number: Int, expected: Int) {

        assertEquals(expected, number.negative())
    }

    enum class SampleEnum(
        val key: Int
    ){
        A(21),
        B(19);
    }

    @Test
    fun `IntExtensions toEnum enum standard parsing is correct`() {

        assertEquals(SampleEnum.A, 0.toEnum<SampleEnum>())
        assertEquals(SampleEnum.B, 1.toEnum<SampleEnum>())
        assertNull(4.toEnum<SampleEnum>())
    }


    @Test
    fun `IntExtensions toEnum custom enum parsing is correct`() {

        assertEquals(SampleEnum.A, 21.toEnum<SampleEnum> { it.key })
        assertEquals(SampleEnum.B, 19.toEnum<SampleEnum> { it.key })
        assertNull(20.toEnum<SampleEnum> { it.key })
    }

    //endregion

}