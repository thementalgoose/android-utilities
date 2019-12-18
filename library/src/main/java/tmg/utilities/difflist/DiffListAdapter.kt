package tmg.utilities.difflist

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class DiffListAdapter<MODEL, VIEW_HOLDER : RecyclerView.ViewHolder>(
    private val diffListComparator: DiffListComparator<MODEL>
): RecyclerView.Adapter<VIEW_HOLDER>(){
    private val items = mutableListOf<MODEL>()

    fun replaceAll(newItems: List<MODEL>) {
        val diffResult = DiffUtil.calculateDiff(DiffListComparatorCallback(items, newItems, diffListComparator))

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