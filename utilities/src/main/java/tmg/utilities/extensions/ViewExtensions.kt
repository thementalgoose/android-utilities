package tmg.utilities.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


//region Visibility

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

//endregion

//region Keyboard

fun View.closeKeyboard() {
    val inputManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.hideSoftInputFromWindow(this.windowToken, 0)
}

//endregion

//region Focus Keyboard

fun EditText.focusKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}