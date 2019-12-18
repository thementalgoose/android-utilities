package tmg.utilities.extensions.views

import android.view.MotionEvent
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Add an item divider to a recyclerview in a vertical orientation
 */
fun RecyclerView.addItemDivider(@DrawableRes divider: Int) {
    val itemDecorator = androidx.recyclerview.widget.DividerItemDecoration(
        context,
        androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
    )
    itemDecorator.setDrawable(ContextCompat.getDrawable(context!!, divider)!!)
    this.addItemDecoration(itemDecorator)
}

/**
 * Disable all user input to a recyclerview, passing touch events out
 */
fun RecyclerView.disableTouch() {
    this.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            return true
        }
    })
}