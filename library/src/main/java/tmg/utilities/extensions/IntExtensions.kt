package tmg.utilities.extensions

import android.content.Context
import android.content.res.Resources
import tmg.utilities.utils.DensityUtils
import kotlin.math.floor

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



/**
 * Given that the number is in seconds, convert it to a pair containing hours and minutes
 */
val Int.secondsToHHmm: Pair<Int, Int>
    get() {
        if (this < 0) {
            return Pair(0, 0)
        }
        val hours = floor(this / 3600f).toInt()
        val minutes = floor((this % 3600f) / 60f).toInt()
        return Pair(hours, minutes)
    }

//endregion

//region Strings

fun Int.toEmptyIfZero(): String = if (this == 0) "" else this.toString()

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

//region Colours

/**
 * Get the hex colour code of an integer of a colour
 * @return Capital letter color codes (ie. FF00FF)
 */
val Int.hexColor: String
    get() = String.format("#%06X", (0xFFFFFF and this))

//endregion