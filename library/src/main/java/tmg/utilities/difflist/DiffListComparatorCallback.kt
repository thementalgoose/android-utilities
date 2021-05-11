package tmg.utilities.difflist

import androidx.recyclerview.widget.DiffUtil

@Deprecated("Usage is no longer recommended, use the standard implementation", replaceWith = ReplaceWith("DiffUtil.Callback()"))
class DiffListComparatorCallback<MODEL>(
    private val oldItems: List<MODEL>,
    private val newItems: List<MODEL>,
    private val listComparator: DiffListComparator<MODEL>
): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return listComparator.areItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return listComparator.areContentsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }
}