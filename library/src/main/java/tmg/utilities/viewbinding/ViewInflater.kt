package tmg.utilities.viewbinding

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

fun <T: ViewBinding> ((inflater: LayoutInflater) -> T).toViewInflater(): ViewInflater<T> {
    return object : ViewInflater<T> {
        override fun inflate(layoutInflater: LayoutInflater) = this@toViewInflater.invoke(layoutInflater)
    }
}
interface ViewInflater<T: ViewBinding> {
    fun inflate(layoutInflater: LayoutInflater): T
}