package tmg.utilities.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.CsvSource
import tmg.utilities.enums.PermissionRequestState


internal class PermissionRequestResultTest {

    @Test
    fun `PermissionRequestResult isAllGranted returns true when rationale and denied is empty`() {
        val model = PermissionRequestResult(
            granted = listOf("granted"),
            showRational = emptyList(),
            denied = emptyList()
        )

        assertTrue(model.isAllGranted)
    }

    @Test
    fun `PermissionRequestResult isAllGranted returns false when rationale has a value`() {
        val model = PermissionRequestResult(
            granted = listOf("granted"),
            showRational = listOf("rationale"),
            denied = emptyList()
        )

        assertFalse(model.isAllGranted)
    }

    @Test
    fun `PermissionRequestResult isAllGranted returns false when denied has a value`() {
        val model = PermissionRequestResult(
            granted = listOf("granted"),
            showRational = emptyList(),
            denied = listOf("denied")
        )

        assertFalse(model.isAllGranted)
    }

    @Test
    fun `PermissionRequestResult isAllGranted returns false when denied and rationale have values`() {
        val model = PermissionRequestResult(
            granted = listOf("granted"),
            showRational = listOf("rationale"),
            denied = listOf("denied")
        )

        assertFalse(model.isAllGranted)
    }

    @Test
    fun `PermissionRequestResult isAllGranted returns true if result contains no permissions`() {
        val model = PermissionRequestResult(
            granted = emptyList(),
            showRational = emptyList(),
            denied = emptyList()
        )

        assertFalse(model.isAllGranted)
    }

    @CsvSource(
        "granted,GRANTED",
        "rationale,SHOW_RATIONALE",
        "denied,DENIED",
        ",DENIED",
    )
    @Test
    fun `get permission returns granted`(permission: String, expectedState: PermissionRequestState) {
        val model = PermissionRequestResult(
            granted = listOf("granted"),
            showRational = listOf("rationale"),
            denied = listOf("denied")
        )
        assertEquals(expectedState, model.get(permission))
    }
}