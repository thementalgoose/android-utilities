package tmg.utilities.extensions.views

import android.graphics.Paint
import android.widget.TextView

/**
 * Underline the text in a textview
 */
fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}