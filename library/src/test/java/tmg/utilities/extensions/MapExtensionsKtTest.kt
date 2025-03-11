package tmg.utilities.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MapExtensionsKtTest {

    @Test
    fun `MapExtensions keySet extract key set from valid list properly`() {
        val sampleMap: Map<String, Int> = mapOf(
            "b" to 2,
            "a" to 1,
            "c" to 3
        )

        assertEquals(setOf("a", "b", "c"), sampleMap.keySet())
    }

    @Test
    fun `MapExtensions valueSet extract value set from valid list properly`() {
        val sampleMap: Map<String, Int> = mapOf(
            "b" to 2,
            "a" to 1,
            "c" to 3
        )

        assertEquals(setOf(1, 2, 3), sampleMap.valueSet())
    }
}