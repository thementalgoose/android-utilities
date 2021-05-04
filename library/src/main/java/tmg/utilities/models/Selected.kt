package tmg.utilities.models

data class Selected<T>(
    val value: T,
    val isSelected: Boolean = false
)