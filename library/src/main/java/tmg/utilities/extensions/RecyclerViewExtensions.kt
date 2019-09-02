package tmg.utilities.extensions

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addItemDivider(@DrawableRes divider: Int) {
    val itemDecorator = androidx.recyclerview.widget.DividerItemDecoration(
        context,
        androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
    )
    itemDecorator.setDrawable(androidx.core.content.ContextCompat.getDrawable(context!!, divider)!!)
    this.addItemDecoration(itemDecorator)
}