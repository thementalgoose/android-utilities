package tmg.utilities.viewbinding

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding

fun <T: ViewBinding> ((inflater: View) -> T).toViewBinder(): ViewBinder<T> {
    return object : ViewBinder<T> {
        override fun bind(view: View) = this@toViewBinder.invoke(view)
    }
}

interface ViewBinder<T: ViewBinding> {
    fun bind(view: View): T
}