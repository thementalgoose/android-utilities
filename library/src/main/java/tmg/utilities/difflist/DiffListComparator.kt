package tmg.utilities.difflist

@Deprecated("Usage is no longer recommended, use the standard implementation", replaceWith = ReplaceWith("DiffUtil.Callback()"))
interface DiffListComparator<MODEL> {
    fun areItemsTheSame(o1: MODEL, o2: MODEL): Boolean
    fun areContentsTheSame(o1: MODEL, o2: MODEL): Boolean
}