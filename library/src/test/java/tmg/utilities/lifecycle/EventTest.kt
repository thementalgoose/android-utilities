package tmg.utilities.lifecycle

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EventTest {

    @Test
    fun `processed returns true then false once called`() {
        val event = Event()

        assertTrue(event.processEvent)
        assertFalse(event.processEvent)
        assertFalse(event.processEvent)
    }
}