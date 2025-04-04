package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate
import java.time.LocalTime

class StringExtensionsKtTest {

    //region Dates

    @ParameterizedTest(name = "Hours extracted from {0} = {1}")
    @CsvSource(
        "12:00,12",
        "21:23,21",
        "dshiofsdilf,",
        "1239:2312,",
        "27181729,",
        "2112.13123921,"
    )
    fun `StringExtensions hours checking hours can be parsed from strings properly`(source: String, expected: Int?) {

        assertEquals(expected, source.hours())
    }
    @ParameterizedTest(name = "Total minutes extracted from {0} = {1}")
    @CsvSource(
        "12:00,720",
        "21:23,1283",
        "dshiofsdilf,",
        "1239:2312,",
        "27181729,",
        "2112.13123921,"
    )
    fun `StringExtensions minsTotal checking mins can be parsed from strings properly`(source: String, expected: Int?) {

        assertEquals(expected, source.minsTotal())
    }

    @ParameterizedTest
    @CsvSource(
        "12:00,0",
        "21:23,23",
        "dshiofsdilf,",
        "1239:2312,",
        "27181729,",
        "2112.13123921,"
    )
    fun `StringExtensions minsPastHour checking mins past hour can be parsed from strings properly`(source: String, expected: Int?) {

        assertEquals(expected, source.minsPastHour())
    }

    //endregion

    //region LocalDate + LocalTime

    @ParameterizedTest(name = "\"{0}\".toLocalDate() is valid")
    @CsvSource(
        "1995-11-12",
        "1995-November-12"
    )
    fun `StringExtensions toLocalDate converts to local date`(input: String) {
        val expected = LocalDate.of(1995, 11, 12)

        assertEquals(expected, input.toLocalDate())
    }

    @Test
    fun `StringExtensions toLocalDate converts to local with valid custom format`() {
        val input = "1937-11-10"
        val expected = LocalDate.of(1937, 10, 11)

        assertEquals(expected, input.toLocalDate("yyyy-dd-MM"))
    }

    @Test
    fun `StringExtensions toLocalDate converts to null with invalid custom format`() {
        val input = "1937-11-13"

        assertNull(input.toLocalDate("yyyy-dd-MM"))
    }

    @ParameterizedTest(name = "\"{0}\".toLocalTime() is valid")
    @CsvSource(
        "09:10:11",
        "09:10:11Z",
        "09:10:11.000"
    )
    fun `StringExtensions toLocalTime converts to local time`(input: String) {
        val expected = LocalTime.of(9, 10, 11)

        assertEquals(expected, input.toLocalTime())
    }

    @Test
    fun `StringExtensions toLocalTime converts to local with valid custom format`() {
        val input = "59:18:00"
        val expected = LocalTime.of(18, 59, 0)

        assertEquals(expected, input.toLocalTime("mm:HH:ss"))
    }

    @Test
    fun `StringExtensions toLocalTime converts to null with invalid custom format`() {
        val input = "11:27:00"

        assertNull(input.toLocalTime("mm:HH:ss"))
    }

    //endregion

    //region Length

    @ParameterizedTest
    @CsvSource(
        "source,0,8,source00",
        "abd,n,2,abd",
        "abd,n,3,abd",
        "abd,n,4,abdn",
        "abd,3,5,abd33"
    )
    fun `StringExtensions extendWith extends strings properly`(source: String, char: Char, toLength: Int, expected: String) {

        assertEquals(expected, source.extendWith(char, toLength))
    }

    //endregion

    //region Regexes

    @ParameterizedTest
    @CsvSource(
        "validemail@email.com,true",
        "anothervalidemail@email.co.uk,true",
        "@email.com,false",
        "help@me,false"
    )
    fun `StringExtensions isEmail testing valid and invalid emails`(email: String, isValid: Boolean) {

        assertEquals(isValid, email.isEmail())
    }

    @ParameterizedTest
    @CsvSource(
        "http://somekindofurl,true",
        "file://something,true",
        "://help,false",
        "not/a/path,false"
    )
    fun `StringExtensions isUrl testing valid and invalid urls`(url: String, isValid: Boolean) {

        assertEquals(isValid, url.isUrl())
    }

    @ParameterizedTest
    @CsvSource(
        "http://somekindofurl,true",
        "https://something,true",
        "://help,false",
        "websiteUrl.com,false"
    )
    fun `StringExtensions isHttp testing valid and invalid http`(http: String, isValid: Boolean) {

        assertEquals(isValid, http.isHttp())
    }

    //endregion

    //region Enums

    enum class SampleEnum(
        val key: String
    ){
        A("z"),
        B("y");
    }

    @Test
    fun `StringExtensions toEnum enum standard parsing is correct`() {

        assertEquals(SampleEnum.A, "A".toEnum<SampleEnum>())
        assertEquals(SampleEnum.B, "B".toEnum<SampleEnum>())
        assertNull("UNSUPPORTED".toEnum<SampleEnum>())
    }


    @Test
    fun `StringExtensions toEnum custom enum parsing is correct`() {

        assertEquals(SampleEnum.A, "z".toEnum<SampleEnum> { it.key })
        assertEquals(SampleEnum.B, "y".toEnum<SampleEnum> { it.key })
        assertNull("UNSUPPORTED".toEnum<SampleEnum> { it.key })
    }

    //endregion

    //regions Encryption

    @ParameterizedTest(name = "{0} >> MD5 >> {1}")
    @CsvSource(
        "testing,ae2b1fca515949e5d54fb22b8ed95575",
        "sample_password,e15d45d5e4030015e668ba8f863569ce"
    )
    fun `StringExtensions md5 test md5 generation`(input: String, expectedHash: String) {

        assertEquals(expectedHash.lowercase(), input.md5.lowercase())
    }

    @ParameterizedTest(name = "{0} >> SHA1 >> {1}")
    @CsvSource(
        "testing,DC724AF18FBDD4E59189F5FE768A5F8311527050",
        "sample_password,FDC625010C4BEB998E590924DF39B7E59298612D"
    )
    fun `StringExtensions sha1 test sha1 generation`(input: String, expectedHash: String) {

        assertEquals(expectedHash.lowercase(), input.sha1.lowercase())
    }

    //endregion
}