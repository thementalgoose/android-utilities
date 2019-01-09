package tmg.utilities.utils

import android.content.res.Resources
import android.util.TypedValue
import android.util.DisplayMetrics

class DensityUtils {
    companion object {
        /**
         * Convert a DP value to a PX value
         *
         * @param res App resources
         * @param dp The value of dp
         */
        @JvmStatic
        fun toPx(res: Resources, dp: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.displayMetrics)
        }

        /**
         * Convert a PX value to a DP value
         *
         * @param res App resources
         * @param px The value of px
         */
        @JvmStatic
        fun toDp(res: Resources, px: Float): Float {
            return px / (res.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }
}