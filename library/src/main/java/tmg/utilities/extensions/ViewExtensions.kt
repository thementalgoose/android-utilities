package tmg.utilities.extensions

import android.content.Context
import android.graphics.Rect
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.disposables.Disposables


//region Visibility

/**
 * Set the visibility of the view to View.SHOW
 */
fun View.show() {
    visible()
}

/**
 * Set the visibility of the view to View.VISIBLE
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Set the visibility of the view to View.INVISIBLE
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Set the visibility of the view to View.GONE
 */
fun View.hide() {
    gone()
}

/**
 * Set the visibility of the view to View.GONE
 */
fun View.gone() {
    visibility = View.GONE
}

//endregion

//region Keyboard

/**
 * Close the keyboard from the given view
 */
fun View.closeKeyboard() {
    val inputManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.hideSoftInputFromWindow(this.windowToken, 0)
}

/**
 * Focus the keyboard on a given edit text if it can
 */
fun EditText.focusKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

//endregion

//region Bounds

/**
 * Rectangle describing the bounds of the view
 */
fun View.rect(): Rect {
    val l = IntArray(2)
    getLocationOnScreen(l)
    return Rect(l[0], l[1], l[0] + width, l[1] + height)
}

//endregion