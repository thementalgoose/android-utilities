package tmg.utilities.sortedlist

@Deprecated("SortedListAdapter is deprecated, please use a DiffList")
interface SortedListComparator<E> : Comparator<E> {
    fun equal(obj1: E, obj2: E): Boolean
}