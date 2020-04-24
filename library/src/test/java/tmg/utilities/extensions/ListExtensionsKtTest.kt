package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ListExtensionsKtTest {

    @Test
    fun `ListExtensions keySet extract key set from valid list properly`() {
        val sampleMap: Map<String, Int> = mapOf(
            "b" to 2,
            "a" to 1,
            "c" to 3
        )

        assertEquals(setOf("a", "b", "c"), sampleMap.toList().keySet())
    }
}