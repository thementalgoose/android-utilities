package tmg.utilities.extensions

import android.graphics.Color
import androidx.core.graphics.toColorInt
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class IntExtensionsKtTest {

    @ParameterizedTest(name = "")
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
        "1,1",
        ",0"
    )
    fun `IntExtensions positive making a number positive`(number: Int?, expected: Int) {
        assertEquals(expected, number.positive())
    }

    @ParameterizedTest
    @CsvSource(
        "-1,-1",
        "0,0",
        "1,0",
        ",0"
    )
    fun `IntExtensions negative making a number negative`(number: Int?, expected: Int) {
        assertEquals(expected, number.negative())
    }

    @ParameterizedTest
    @CsvSource(
        "86400,",
        "86399,23:59",
        "43200,12:00",
        "0,00:00",
        "-1,"
    )
    fun `IntExtensions secondsToHHmm formats a valid time`(seconds: Int, expectedTime: String?) {
        assertEquals(expectedTime, seconds.secondsToHHmm())
    }

    @ParameterizedTest
    @CsvSource(
        "3,0,4,0003",
        "0,1,2,10",
        "-1,2,4,-1",
        "34,A,4,AA34"
    )
    fun `IntExtensions toLength calculates correct string length`(value: Int, extendWithChar: Char?, numberOfDigits: Int, expected: String) {
        assertEquals(expected, value.extend(numberOfDigits, extendWithChar ?: '0'))
    }

    @Test
    fun `IntExtensions itemsOf check items generate properly`() {
        val indexCheck: List<Int> = listOf(0,1,2,3)
        assertEquals(indexCheck, 4.itemsOf { it })


        val lookup: Map<Int, String> = mapOf(
            0 to "1234",
            1 to "5678",
            2 to "1357"
        )
        val expected: List<String> = lookup.values.toList()
        assertEquals(expected, 3.itemsOf { lookup[it] })
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

    @ParameterizedTest
    @CsvSource(
        "1,1st",
        "2,2nd",
        "3,3rd",
        "4,4th",
        "9,9th",
        "11,11th",
        "12,12th",
        "13,13th",
        "21,21st",
        "22,22nd",
        "23,23rd",
        "50,50th",
        "1142,1142nd",
        "12711,12711th",
        "234567,234567th",
        "0,0",
        "-1,-1"
    )
    fun `IntExtensions ordinalAbbreviation and elevatedTerminal numbers are converted correctly`(value: Int, expectedValue: String) {

        assertEquals(expectedValue, value.ordinalAbbreviation)
        assertEquals(expectedValue, value.elevatedTerminal)
    }

    @Test
    fun `IntExtensions hexColor converts sample colour properly`() {

        assertEquals("#FF0000", Color.RED.hexColor)
        assertEquals("#0000FF", Color.BLUE.hexColor)
        assertEquals("#00FF00", Color.GREEN.hexColor)
        assertEquals("#FFFFFF", Color.WHITE.hexColor)
        assertEquals("#000000", Color.BLACK.hexColor)
        assertEquals("#96A1FD", 127312381.hexColor)
    }

    @ParameterizedTest
    @CsvSource(
        "-1,-1",
        "0,",
        "1,1",
        "11231231,11231231"
    )
    fun `IntExtensions toEmptyIfZero check empty if zero works`(value: Int, assertValue: String?) {
        val expectedValue = assertValue ?: ""

        assertEquals(expectedValue, value.toEmptyIfZero())
    }

    //endregion

}