package tmg.utilities.utils

import android.graphics.Color
import android.util.Log
import androidx.annotation.ColorInt
import java.text.ParseException
import androidx.core.graphics.toColorInt
import kotlin.math.roundToInt

class ColorUtils {
    companion object {
        @ColorInt
        @JvmStatic
        fun darken(@ColorInt color: Int, factor: Float = 0.8f): Int {
            return manipulate(color, factor)
        }

        @ColorInt
        @JvmStatic
        fun lighten(@ColorInt color: Int, factor: Float = 1.2f): Int {
            return manipulate(color, factor)
        }

        @ColorInt
        @JvmStatic
        fun manipulate(@ColorInt color: Int, factor: Float): Int {
            val a = Color.alpha(color)
            val r = (Color.red(color) * factor).roundToInt()
            val g = (Color.green(color) * factor).roundToInt()
            val b = (Color.blue(color) * factor).roundToInt()
            return Color.argb(a,
                r.coerceAtMost(255),
                g.coerceAtMost(255),
                b.coerceAtMost(255)
            )
        }

        @ColorInt
        @JvmStatic
        @Throws(IllegalArgumentException::class)
        fun parse(color: String): Int {
            return try {
                color.toColorInt()
            } catch (e: ParseException) {
                Log.d("ColorUtils", "Colour $color cannot be parsed")
                Color.WHITE
            }
        }

        @JvmOverloads
        @JvmStatic
        fun contrastTextLight(
            @ColorInt color: Int,
            threshold: Int = 180,
            redMultiplier: Float = 0.299f,
            greenMultiplier: Float = 0.587f,
            blueMultiplier: Float = 0.114f
        ): Boolean {
            val score = (Color.red(color) * redMultiplier) +
                    (Color.green(color) * greenMultiplier) +
                    (Color.blue(color) * blueMultiplier)
            return score < threshold
        }

        @JvmOverloads
        @JvmStatic
        fun contrastTextDark(
            @ColorInt color: Int,
            threshold: Int = 180,
            redMultiplier: Float = 0.299f,
            greenMultiplier: Float = 0.587f,
            blueMultiplier: Float = 0.114f
        ): Boolean {
            return !contrastTextLight(color, threshold, redMultiplier, greenMultiplier, blueMultiplier)
        }
    }
}