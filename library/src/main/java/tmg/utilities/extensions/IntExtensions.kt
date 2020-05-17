package tmg.utilities.extensions

import android.content.Context
import android.content.res.Resources
import tmg.utilities.utils.DensityUtils

//region Density conversions

/**
 * Convert a DP value to a PX value
 *
 * @param res App resources
 */
fun Int.dpToPx(res: Resources): Float {
    return DensityUtils.toPx(res, this.toFloat())
}

/**
 * Convert a PX value to a DP value
 *
 * @param res App resources
 */
fun Int.pxToDp(res: Resources): Float {
    return DensityUtils.toDp(res, this.toFloat())
}

//endregion

//region Capping

/**
 * Cap an integer to a specific min and max value
 *
 * @param min If the number is below the min, then cap it to the min
 * @param max If the number is below the max, then cap it to the max
 */
@Deprecated("No longer required", replaceWith = ReplaceWith("coerceIn(minValue, maxValue)"))
fun Int.capTo(min: Int, max: Int): Int {
    if (this < min) return min
    if (this > max) return max
    return this
}

//endregion

//region To String

/**
 * Display number as 2 digit string
 */
@Deprecated("No longer used", ReplaceWith("Int.extend(numberOfDigits, extendWithChar)"))
fun Int.dp2(): String {
    return extend(2, extendWithChar = '0')
}

/**
 * Convert an integer to a string but with a given length
 *
 * ie. 2.toLength(2) = "02"
 * ie. 34.toLength(1) = "34"
 */
@Deprecated("No longer used", ReplaceWith("Int.extend(numberOfDigits, extendWithChar)"))
fun Int.toLength(numberOfDigits: Int) {
    this.extend(numberOfDigits, extendWithChar = '0')
}

/**
 * Convert an integer to a string but with a given length
 *
 * ie. 2.toLength(2) = "02"
 * ie. 34.toLength(1) = "34"
 * ie. 34.toLength(4, extendWithDigit = 'A') = "AA34"
 *
 * @param numberOfDigits The number of digits we should make this integer
 */
fun Int.extend(numberOfDigits: Int, extendWithChar: Char = '0'): String {
    if (this < 0) {
        return this.toString()
    }
    if (this.toString().length >= numberOfDigits) {
        return this.toString()
    }
    val numberToAdd = numberOfDigits - this.toString().length
    return "${numberToAdd.itemsOf { extendWithChar }.joinToString(separator = "")}$this"
}

//endregion

//region Duplication

fun <T> Int.itemsOf(runner: (index: Int) -> T): List<T> {
    return List(this) { runner(it) }
}

//endregion

//region Time

/**
 * Convert a number of seconds into a HH:mm representation, if the seconds is < 1440 and > 0
 * (ie. A time format can made for it)
 */
fun Int.secondsToHHmm(): String? {
    if (this >= (86400)) return null
    if (this < 0) return null
    val hours: String = (this / (60 * 60)).extend(2)
    val minutes: String = ((this / 60) % 60).extend(2)
    return "$hours:$minutes"
}

//endregion

//region Strings

fun Int.toStringResource(context: Context): String {
    return context.getString(this)
}

//endregion

//region Ordinal Abbreviations

/**
 * Convert a number into it's "ordinal abbreviation" or "elevated terminal"
 * Basically converting 1 into 1st, 11 into 11th, 33 into 33rd
 */
val Int.elevatedTerminal: String
    get() = this.ordinalAbbreviation

/**
 * Convert a number into it's "ordinal abbreviation" or "elevated terminal"
 * Basically converting 1 into 1st, 11 into 11th, 33 into 33rd
 */
val Int.ordinalAbbreviation: String
    get() {
        val string = this.toString()
        return if (this <= 0) {
            string
        } else if (string.endsWith("11") ||
            string.endsWith("12") ||
            string.endsWith("13")) {
            "${this}th"
        } else if (string.endsWith("1")) {
            "${this}st"
        } else if (string.endsWith("2")) {
            "${this}nd"
        } else if (string.endsWith("3")) {
            "${this}rd"
        } else {
            "${this}th"
        }
    }

//endregion

/**
 * Make any int value capped as a positive value
 */
fun Int?.positive(): Int {
    return if (this == null || this < 0) 0 else this
}

/**
 * Make any int value capped as a positive value
 */
fun Int?.negative(): Int {
    return if (this == null || this > 0) 0 else this
}

//region Enums

/**
 * Convert any int value into it's enum value by a given value
 *
 * ```
 * MyEnum(val id: Int) { FIRST(1), SECOND(2) }
 * ```
 *
 * 1.toEnum<MyEnum>()            -> SECOND (because ordinal = 1)
 * 1.toEnum<MyEnum>() { it.id }  -> FIRST (because id == 1)
 *
 * @param by Function to run to determine what to match on (by default the ordinal)
 */
inline fun <reified T : Enum<T>> Int.toEnum(by: (enum: T) -> Int = { it.ordinal }): T? {
    return enumValues<T>().firstOrNull { by(it) == this }
}

//endregion