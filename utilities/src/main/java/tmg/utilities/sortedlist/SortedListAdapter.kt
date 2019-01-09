package tmg.utilities.sortedlist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList

/**
 * Created by jordan on 16/10/2017
 */
abstract class SortedListAdapter<E, T : RecyclerView.ViewHolder>: RecyclerView.Adapter<T> {

    lateinit var mSortedList: SortedList<E>
    private var mClass: Class<E>
    private var mComparator: SortedListComparator<E>?

    constructor(mClass: Class<E>, mComparator: SortedListComparator<E>? = null) : super() {
        this.mClass = mClass
        this.mComparator = mComparator
        init()
    }

    private fun init() {
        mSortedList = SortedList<E>(mClass, object : SortedList.Callback<E>() {
            override fun onRemoved(position: Int, count: Int) {
                notifyItemRangeRemoved(position, count)
            }

            override fun onChanged(position: Int, count: Int) {
                notifyItemRangeChanged(position, count)
            }

            override fun onInserted(position: Int, count: Int) {
                notifyItemRangeInserted(position, count)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun areItemsTheSame(item1: E, item2: E): Boolean {
                mComparator?.let {
                    return it.equal(item1, item2)
                }
                return item1?.equals(item2) ?: false
            }

            override fun compare(o1: E, o2: E): Int {
                mComparator?.let {
                    return it.compare(o1, o2)
                }
                return o1!!.hashCode() - o2!!.hashCode()
            }

            override fun areContentsTheSame(oldItem: E, newItem: E): Boolean {
                mComparator?.let {
                    return it.equal(oldItem, newItem)
                }
                return oldItem?.equals(newItem) ?: false
            }

        })
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        onBindViewHolder(holder, position, mSortedList.get(position))
    }

    abstract fun onBindViewHolder(holder: T, position: Int, item: E)

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T

    override fun getItemCount(): Int {
        return mSortedList.size()
    }

    fun get(pos: Int): E? {
        return if (mSortedList.size() > pos && pos >= 0) {
            mSortedList.get(pos)
        } else {
            null
        }
    }

    //region Add / REMOVE methods

    fun add(model: E) {
        mSortedList.add(model)
    }

    fun remove(model: E) {
        mSortedList.remove(model)
    }

    fun add(models: List<E>) {
        mSortedList.addAll(models)
    }

    fun remove(models: List<E>) {
        mSortedList.beginBatchedUpdates()
        for (model in models) {
            mSortedList.remove(model)
        }
        mSortedList.endBatchedUpdates()
    }

    fun removeAll() {
        if (mSortedList.size() > 0) {
            mSortedList.beginBatchedUpdates()
            for (i in mSortedList.size() - 1..0) {
                mSortedList.remove(mSortedList[i])
            }
            mSortedList.endBatchedUpdates()
        }
    }

    fun replace(model: E) {
        mSortedList.beginBatchedUpdates()
        for (i in mSortedList.size() - 1 downTo 0) {
            val mod = mSortedList.get(i)
            if (mComparator != null) {
                if (mComparator!!.equal(mod, model)) {
                    mSortedList.remove(mod)
                }
            }
            else {
                if (mod?.equals(model) == true) {
                    mSortedList.remove(mod)
                }
            }
        }
        mSortedList.add(model)
        mSortedList.endBatchedUpdates()
    }

    fun replaceAll(models: List<E>) {
        mSortedList.beginBatchedUpdates()
        for (i in mSortedList.size() - 1 downTo 0) {
            val model = mSortedList.get(i)
            if (!models.contains(model)) {
                mSortedList.remove(model)
            }
        }
        mSortedList.addAll(models)
        mSortedList.endBatchedUpdates()
    }

    //endregion
}
