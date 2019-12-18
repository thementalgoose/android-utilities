package tmg.utilities.extensions

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView

/**
 * Take some text, highlight some text with a color and add a click listener to it
 * @param source Text to click
 * @param color Color to change too, default = null,
 * @param onClick Callback
 */
fun SpannableString.onClick(source: String, shouldUnderline: Boolean = true, shouldBold: Boolean = true, color: Int? = null, textView: TextView? = null, onClick: () -> Unit): SpannableString {
    val startIndex = this.toString().indexOf(source)
    if (startIndex == -1) {
        throw Exception("Cannot highlight this title as $source is not contained with ${this}")
    }
    this.setSpan(object : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            if (color != null) {
                ds.color = color
                ds.bgColor = Color.TRANSPARENT
            }
        }

        override fun onClick(widget: View) {
            onClick()
            textView?.clearFocus()
            textView?.invalidate()
        }
    }, startIndex, startIndex + source.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    if (shouldUnderline) {
        this.setSpan(UnderlineSpan(), startIndex, startIndex + source.length, 0)
    }
    if (shouldBold) {
        this.setSpan(StyleSpan(Typeface.BOLD), startIndex, startIndex + source.length, 0)
    }
    return this
}

/**
 * Highlight a given word in a string with a given colour
 */
fun String.highlight(source: String, color: Int): SpannableString {
    val startIndex = this.indexOf(source)
    if (startIndex == -1) {
        throw Exception("Cannot highlight this title as $source is not contained with ${this}")
    }
    val spannable = SpannableString(this)
    spannable.setSpan(object : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            ds.color = color
        }
        override fun onClick(widget: View) {}
    }, startIndex, startIndex + source.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannable
}