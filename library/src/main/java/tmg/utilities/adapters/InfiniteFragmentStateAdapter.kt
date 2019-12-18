package tmg.utilities.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

abstract class InfiniteFragmentStateAdapter : FragmentStateAdapter {

    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(
        fragmentManager,
        lifecycle
    )

    constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)
    constructor(fragment: Fragment) : super(fragment)

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        return createInfiniteFragment(position.toInfinitePosition())
    }

    abstract fun createInfiniteFragment(position: Int): Fragment

    fun Int.toInfinitePosition(): Int = this - (Integer.MAX_VALUE / 2)

    fun Int.toPosition(): Int = this + (Integer.MAX_VALUE / 2)
}
