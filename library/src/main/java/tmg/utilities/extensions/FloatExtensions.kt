package tmg.utilities.extensions

import android.content.res.Resources
import tmg.utilities.utils.DensityUtils

//region Density conversions

/**
 * Convert a DP value to a PX value
 *
 * @param res App resources
 */
fun Float.dpToPx(res: Resources): Float {
    return DensityUtils.toPx(res, this)
}

/**
 * Convert a PX value to a DP value
 *
 * @param res App resources
 */
fun Float.pxToDp(res: Resources): Float {
    return DensityUtils.toDp(res, this)
}

//endregion

//region Precision

fun Float.toDecimalPlacesString(dp: Int): String {
    return "%.${dp}f".format(this)
}
fun Float.toDecimalPlaces(dp: Int): Float {
    return toDecimalPlacesString(dp).toFloat()
}

//endregion

//region Positive values only

/**
 * Make any float value capped as a positive value
 */
fun Float.positive(): Float {
    return if (this < 0.0f) {
        0.0f
    } else {
        this
    }
}

//endregion