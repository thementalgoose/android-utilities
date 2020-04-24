package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*


private const val date1: Long = 1587749084000 // 24/04/2020 18:24:44
private const val date2: Long = 1509793753000 // 04/11/2017 11:09:13

class LongExtensionsKtTest {

    // TODO: Move to LocalDate and LocalTime extensions

    @Test
    fun `LongExtensions toDate checking dates get converted properly`() {

        assertEquals("24/04/2020 18:24:44", date1.toDate().toFormat("dd/MM/yyyy HH:mm:ss"))
        assertEquals("04/11/2017 11:09:13", date2.toDate().toFormat("dd/MM/yyyy HH:mm:ss"))
    }

    @Test
    fun `LongExtensions toDateString checking dates get converted properly`() {

        assertEquals("24/04/2020 18:24:44", date1.toDateString("dd/MM/yyyy HH:mm:ss"))
        assertEquals("04/11 - 2017 11:09|13", date2.toDateString("dd/MM - yyyy HH:mm|ss"))
    }

}