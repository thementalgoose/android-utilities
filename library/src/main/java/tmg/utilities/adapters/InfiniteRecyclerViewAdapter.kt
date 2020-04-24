
package tmg.utilities.adapters

import androidx.recyclerview.widget.RecyclerView

abstract class InfiniteRecyclerViewAdapter<VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    override fun getItemCount(): Int = Integer.MAX_VALUE

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindInfiniteViewHolder(holder, position.toInfinitePosition())
    }

    abstract fun onBindInfiniteViewHolder(holder: VH, position: Int)

    override fun getItemViewType(position: Int): Int {
        return getInfiniteItemViewType(position.toInfinitePosition())
    }

    open fun getInfiniteItemViewType(position: Int): Int = 0

    fun Int.toInfinitePosition(): Int = this - (Integer.MAX_VALUE / 2)

    fun Int.toPosition(): Int = this + (Integer.MAX_VALUE / 2)
}