package tmg.utilities.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NullableTest {

    @Test
    fun `Nullable with not null value`() {
        val nullable = Nullable<String>("value")

        assertFalse(nullable.isNull)
        assertTrue(nullable.isNotNull)
    }

    @Test
    fun `Nullable with null value`() {
        val nullable = Nullable<String>(null)

        assertTrue(nullable.isNull)
        assertFalse(nullable.isNotNull)
    }
}