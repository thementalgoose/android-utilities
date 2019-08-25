package tmg.utilities.extensions

/**
 * Extend a char to a given length (ie. 'A'.length(4) = "4444"
 * @param length Length to extend too
 */
fun Char.toLength(length: Int): String {
    if (length <= 0) {
        return ""
    }
    return "${this}${toLength(length - 1)}"
}