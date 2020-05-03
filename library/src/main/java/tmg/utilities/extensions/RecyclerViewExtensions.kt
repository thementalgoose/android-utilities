package tmg.utilities.extensions

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

@Deprecated("Package has moved to tmg.utilities.extensions.views")
fun RecyclerView.disableTouch() {
    this.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            return true
        }
    })
}