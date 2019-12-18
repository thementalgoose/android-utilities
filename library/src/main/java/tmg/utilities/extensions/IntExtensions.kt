package tmg.utilities.extensions

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import tmg.utilities.utils.DensityUtils
import kotlin.coroutines.coroutineContext

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

//region To String

/**
 * Display number as 2 digit string
 */
fun Int.dp2(): String {
    return toLength(2)
}

/**
 * Convert an integer to a string but with a given length
 *
 * (ie. 2.toLength(2) = "02"
 *
 * @param numberOfDigits The number of digits we should make this integer
 */
fun Int.toLength(numberOfDigits: Int): String {
    return if (this < Math.pow(10.0, (numberOfDigits - 1).toDouble())) {
        return "0" + toLength(numberOfDigits - 1)
    }
    else {
        this.toString()
    }
}

//endregion

//region Time

fun Int.secondsToHHmm(): String {
    val hours: String = (this / (60 * 60)).toLength(2)
    val minutes: String = (this / 60).toLength(2)
    return "$hours:$minutes"
}

//endregion

//region Strings

fun Int.toStringResource(context: Context): String {
    return context.getString(this)
}

//endregion

/**
 * Make any int value capped as a positive value
 */
fun Int.positive(): Int {
    return if (this < 0) {
        0
    } else {
        this
    }
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