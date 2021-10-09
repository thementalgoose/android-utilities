package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.text.SimpleDateFormat
import java.util.*

private const val date1: Long = 1587749084000 // 24/04/2020 17:24:44
private const val date2: Long = 1509793753000 // 04/11/2017 11:09:13

class DateExtensionsKtTest {

    @ParameterizedTest
    @CsvSource(
        "20/04/2020,1,Mon.,Monday",
        "21/04/2020,2,Tue.,Tuesday",
        "22/04/2020,3,Wed.,Wednesday",
        "23/04/2020,4,Thu.,Thursday",
        "24/04/2020,5,Fri.,Friday",
        "25/04/2020,6,Sat.,Saturday",
        "26/04/2020,7,Sun.,Sunday"
    )
    fun `DateExtensions dayOfWeek converts date to represent proper day of week`(date: String, expectedWeekdayInt: Int, expectedWeekdayShort: String, expectedWeekdayLong: String) {

        assertEquals(expectedWeekdayInt, date.toDate("dd/MM/yyyy").dayOfWeek())
        assertEquals(expectedWeekdayShort, date.toDate("dd/MM/yyyy").dayOfWeekString(isShort = true))
        assertEquals(expectedWeekdayLong, date.toDate("dd/MM/yyyy").dayOfWeekString(isShort = false))
    }

    @Test
    fun `DateExtensions dayOfMonth extracts day of month`() {

        assertEquals(24, Date(date1).dayOfMonth())
        assertEquals(4, Date(date2).dayOfMonth())
    }

    @Test
    fun `DateExtensions year extracts year`() {

        assertEquals(2020, Date(date1).year())
        assertEquals(2017, Date(date2).year())
    }

    @ParameterizedTest(name = "Month in {0} is {3} / {2} / month {1}")
    @CsvSource(
        "01/01/2020,1,Jan,January",
        "01/03/2020,3,Mar,March",
        "01/12/2020,12,Dec,December"
    )
    fun `DateExtensions month extracts correct month`(date: String, expectedMonth: Int, expectedShort: String, expectedLong: String) {

        assertEquals(expectedMonth, date.toDate("dd/MM/yyyy").month())
        assertEquals(expectedShort, date.toDate("dd/MM/yyyy").monthString(isShort = true))
        assertEquals(expectedLong, date.toDate("dd/MM/yyyy").monthString(isShort = false))
    }

    @ParameterizedTest(name = "isMonday for {0} is {1}")
    @CsvSource(
        "19/04/2020,false",
        "20/04/2020,true",
        "21/04/2020,false"
    )
    fun `DateExtensions isMonday evaluates`(date: String, value: Boolean) {

        assertEquals(value, date.toDate("dd/MM/yyyy").isMonday())
    }

    @ParameterizedTest(name = "isTuesday for {0} is {1}")
    @CsvSource(
        "20/04/2020,false",
        "21/04/2020,true",
        "22/04/2020,false"
    )
    fun `DateExtensions isTuesday evaluates`(date: String, value: Boolean) {

        assertEquals(value, date.toDate("dd/MM/yyyy").isTuesday())
    }

    @ParameterizedTest(name = "isWednesday for {0} is {1}")
    @CsvSource(
        "21/04/2020,false",
        "22/04/2020,true",
        "23/04/2020,false"
    )
    fun `DateExtensions isWednesday evaluates`(date: String, value: Boolean) {

        assertEquals(value, date.toDate("dd/MM/yyyy").isWednesday())
    }

    @ParameterizedTest(name = "isThursday for {0} is {1}")
    @CsvSource(
        "22/04/2020,false",
        "23/04/2020,true",
        "24/04/2020,false"
    )
    fun `DateExtensions isThursday evaluates`(date: String, value: Boolean) {

        assertEquals(value, date.toDate("dd/MM/yyyy").isThursday())
    }

    @ParameterizedTest(name = "isFriday for {0} is {1}")
    @CsvSource(
        "23/04/2020,false",
        "24/04/2020,true",
        "25/04/2020,false"
    )
    fun `DateExtensions isFriday evaluates`(date: String, value: Boolean) {

        assertEquals(value, date.toDate("dd/MM/yyyy").isFriday())
    }

    @ParameterizedTest(name = "isSaturday for {0} is {1}")
    @CsvSource(
        "24/04/2020,false",
        "25/04/2020,true",
        "26/04/2020,false"
    )
    fun `DateExtensions isSaturday evaluates`(date: String, value: Boolean) {

        assertEquals(value, date.toDate("dd/MM/yyyy").isSaturday())
    }

    @ParameterizedTest(name = "isSunday for {0} is {1}")
    @CsvSource(
        "25/04/2020,false",
        "26/04/2020,true",
        "27/04/2020,false"
    )
    fun `DateExtensions isSunday evaluates`(date: String, value: Boolean) {

        assertEquals(value, date.toDate("dd/MM/yyyy").isSunday())
    }

    @ParameterizedTest(name = "Start of day for {0} is {1}")
    @CsvSource(
        "25/04/2020 10:00:00,25/04/2020 00:00:00",
        "12/04/2020 23:59:59,12/04/2020 00:00:00"
    )
    fun `DateExtensions startOfDay evaluates`(date: String, expected: String) {

        assertEquals(expected, date.toDate("dd/MM/yyyy HH:mm:ss").startOfDay().toFormat("dd/MM/yyyy HH:mm:ss"))
    }

    @ParameterizedTest(name = "Start of week for {0} is {1}")
    @CsvSource(
        "25/04/2020 10:00:00,20/04/2020 00:00:00",
        "12/04/2020 23:59:59,06/04/2020 00:00:00"
    )
    fun `DateExtensions startOfWeek evaluates`(date: String, expected: String) {

        assertEquals(expected, date.toDate("dd/MM/yyyy HH:mm:ss").startOfWeek().toFormat("dd/MM/yyyy HH:mm:ss"))
    }

    @ParameterizedTest(name = "Start of month for {0} is {1}")
    @CsvSource(
        "25/04/2020 10:00:00,01/04/2020 00:00:00",
        "12/04/2020 23:59:59,01/04/2020 00:00:00"
    )
    fun `DateExtensions startOfMonth evaluates`(date: String, expected: String) {

        assertEquals(expected, date.toDate("dd/MM/yyyy HH:mm:ss").startOfMonth().toFormat("dd/MM/yyyy HH:mm:ss"))
    }

    @ParameterizedTest(name = "Seconds difference between {0} and {1} is {2}")
    @CsvSource(
        "10:03:12,12:03:00,7188",
        "10:03:12,10:03:13,1",
        "10:03:12,10:03:12,0",
        "10:03:12,10:03:11,-1"
    )
    fun `DateExtensions secondsDiff returns correct seconds difference`(first: String, second: String, secondsDiff: Int) {

        assertEquals(secondsDiff, first.toDate("HH:mm:ss").secondsDiff(second.toDate("HH:mm:ss")))
    }

    @ParameterizedTest(name = "Minutes difference between {0} and {1} is {2}")
    @CsvSource(
        "10:03:12,12:03:00,119",
        "10:03:12,10:02:12,-1",
        "10:03:12,10:03:12,0",
        "10:03:12,10:04:12,1"
    )
    fun `DateExtensions minutesDiff returns correct seconds difference`(first: String, second: String, secondsDiff: Int) {

        assertEquals(secondsDiff, first.toDate("HH:mm:ss").minutesDiff(second.toDate("HH:mm:ss")))
    }


    /**
     * Convert string to date object
     */
    private fun String.toDate(format: String): Date {
        val simpleDateFormatter = SimpleDateFormat(format)
        return simpleDateFormatter.parse(this)!!
    }
}