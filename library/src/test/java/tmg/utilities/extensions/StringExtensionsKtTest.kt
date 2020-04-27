package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.text.ParseException

class StringExtensionsKtTest {

    //region Dates

    @ParameterizedTest
    @CsvSource(
        "12/12/1999,dd/MM/yyyy,944956800000",
        "6/21/2018 11:32,M/dd/yyyy HH:mm,1529577120000",
        "12/12/1999,dd/MM/HHHH,",
        "12/12/1999,yyyy/MM/dd,",
        "12/12/1999,asdasdas,",
        ",sjdsdfj,"
    )
    fun `StringExtensions toDate formats are parsed properly`(source: String?, format: String, expected: Long?) {

        assertEquals(expected, source?.toDate(format)?.time)
    }

    @ParameterizedTest
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
    @ParameterizedTest
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
}