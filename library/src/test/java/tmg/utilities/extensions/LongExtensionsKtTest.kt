package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

private const val date1: Long = 1587749084000 // 24/04/2020 17:24:44
private const val date2: Long = 1509793753000 // 04/11/2017 10:09:13

class LongExtensionsKtTest {

    // TODO: Move to LocalDate and LocalTime extensions

    @Test
    fun `LongExtensions toDate checking dates get converted properly`() {

        Locale.setDefault(Locale.CANADA)

        val date = Date(date1)
        assertEquals(date1.toDate(), date.time.toDate())
    }
}