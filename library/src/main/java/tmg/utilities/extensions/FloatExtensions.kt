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