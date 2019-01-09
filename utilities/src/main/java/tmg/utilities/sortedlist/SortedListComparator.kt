package tmg.utilities.sortedlist

interface SortedListComparator<E> : Comparator<E> {
    fun equal(obj1: E, obj2: E): Boolean
}