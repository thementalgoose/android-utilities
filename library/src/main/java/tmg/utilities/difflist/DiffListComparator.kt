package tmg.utilities.difflist

interface DiffListComparator<MODEL> {
    fun areItemsTheSame(o1: MODEL, o2: MODEL): Boolean
    fun areContentsTheSame(o1: MODEL, o2: MODEL): Boolean
}