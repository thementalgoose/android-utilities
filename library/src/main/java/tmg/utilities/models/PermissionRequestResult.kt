package tmg.utilities.models

import tmg.utilities.enums.PermissionRequestState

data class PermissionRequestResult(
    val granted: List<String>,
    val showRational: List<String>,
    val denied: List<String>
) {
    val isAllGranted: Boolean
        get() = showRational.isEmpty() && denied.isEmpty()

    fun get(permission: String): PermissionRequestState {
        return when {
            granted.contains(permission) -> {
                PermissionRequestState.GRANTED
            }
            showRational.contains(permission) -> {
                PermissionRequestState.SHOW_RATIONAL
            }
            else -> {
                PermissionRequestState.DENIED
            }
        }
    }
}
