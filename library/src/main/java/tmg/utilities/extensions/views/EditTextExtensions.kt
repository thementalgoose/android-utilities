package tmg.utilities.extensions.views

import android.view.View
import android.widget.EditText

/**
 * Set the text for an EditText if the EditText doesn't already
 *   have focus
 *
 * @param text
 */
fun EditText.textIfNotFocus(text: String) {
    if (!this.hasFocus() && this.text.toString() != text) {
        this.setText(text)
    }
}

/**
 * Refocus an edit text when the focus is lost
 */
fun EditText.refocusWhenLost(delay: Long = 200) {
    onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        if(hasFocus) {
            postDelayed({
                if(!this.hasFocus()) {
                    requestFocus()
                }
            }, delay)
        }
    }
}