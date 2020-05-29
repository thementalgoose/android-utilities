package tmg.utilities.extensions.views

import android.content.Context
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Shortcut to accessing context inside a view holder
 */
val RecyclerView.ViewHolder.context: Context
    get() = itemView.context

/**
 * Shortcut to getting a string inside a view holder
 */
fun RecyclerView.ViewHolder.getString(@StringRes res: Int, vararg formats: Any): String {
    return context.getString(res, *formats)
}