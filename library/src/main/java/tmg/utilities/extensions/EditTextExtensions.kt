package tmg.utilities.extensions

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