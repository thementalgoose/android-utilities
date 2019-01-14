package tmg.utilities.extensions

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

//region To String

/**
 * Convert an integer to a string but with a given length
 *
 * (ie. 2.toLength(2) = "02"
 *
 * @param numberOfDigits The number of digits we should make this integer
 */
fun Int.toLength(numberOfDigits: Int): String {
    return if (this < 10) {
        "0$this"
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