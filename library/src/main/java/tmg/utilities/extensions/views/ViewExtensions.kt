package tmg.utilities.extensions.views

import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import tmg.utilities.extensions.managerInputMethod


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

/**
 * Showing a view and specifying the inverse behavior if not
 *
 * view.show(false, isGone = false) will set it to invisible
 * view.show(false, isGone = true) will set it to gone
 * view.show(true, isGone = true) will set it to visible
 */
fun View.show(value: Boolean, isGone: Boolean = true) {
    if (value) {
        show()
    } else {
        if (isGone) gone() else invisible()
    }
}

//endregion

//region Keyboard

/**
 * Close the keyboard from the given view
 */
fun View.closeKeyboard() {
    this.context.managerInputMethod?.hideSoftInputFromWindow(this.windowToken, 0)
}

/**
 * Focus the keyboard on a given edit text if it can
 */
fun EditText.focusKeyboard() {
    this.context.managerInputMethod?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
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

//region Snackbar

fun View.snackbar(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, func: Snackbar.() -> Unit) {
    val snackbar = Snackbar.make(this, resources.getString(messageRes), length)
    snackbar.func()
    snackbar.show()
}

//endregion

//region PopupMenu

fun View.setOnClickPopupMenu(@MenuRes menuRes: Int, itemClicked: (id: Int) -> Boolean) {
    this.setOnClickListener { view ->
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.inflate(menuRes)
        popupMenu.setOnMenuItemClickListener {
            itemClicked(it.itemId)
        }
        popupMenu.show()
    }
}

//endregion