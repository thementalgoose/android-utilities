package tmg.utilities.extensions

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

fun SpannableString.onClick(source: String, color: Int, onClick: () -> Unit): SpannableString {
    val startIndex = this.toString().indexOf(source)
    if (startIndex == -1) {
        throw Exception("Cannot highlight this title as $source is not contained with ${this}")
    }
    this.setSpan(object : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            ds.color = color
        }

        override fun onClick(widget: View) {
            onClick()
        }
    }, startIndex, startIndex + source.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
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
    val spannable: SpannableString = SpannableString(this)
    spannable.setSpan(object : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            ds.color = color
        }
        override fun onClick(widget: View) {}
    }, startIndex, startIndex + source.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannable
}