package tmg.utilities.models

/**
 * Nullable
 * Holds a value which is nullable inside this object which would not be nullable
 */
data class Nullable<T>(val value: T? = null) {
    val isNull: Boolean = value == null
    val isNotNull: Boolean = value != null

    override fun toString(): String {
        return value?.toString() ?: "null"
    }
}