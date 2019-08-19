package tmg.utilities.difflist

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class DiffListAdapter<MODEL, VIEW_HOLDER : RecyclerView.ViewHolder>(val diffComparator: DiffComparator<MODEL>): RecyclerView.Adapter<VIEW_HOLDER>(){
    val items = mutableListOf<MODEL>()

    fun replaceAll(newItems: List<MODEL>) {
        val diffResult = DiffUtil.calculateDiff(DiffComparatorCallback(items, newItems, diffComparator))

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VIEW_HOLDER, position: Int) {
        onBindViewHolder(holder, position, items[position])
    }

    abstract fun onBindViewHolder(holder: VIEW_HOLDER, position: Int, model: MODEL)
}

class DiffComparatorCallback<MODEL>(val oldItems: List<MODEL>, val newItems: List<MODEL>, val comparator: DiffComparator<MODEL>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return comparator.areItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return comparator.areContentsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }
}

interface DiffComparator<MODEL> {
    fun areItemsTheSame(o1: MODEL, o2: MODEL): Boolean
    fun areContentsTheSame(o1: MODEL, o2: MODEL): Boolean
}