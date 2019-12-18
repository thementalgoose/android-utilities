package tmg.utilities.extensions.views

import android.app.Activity
import android.util.DisplayMetrics
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import tmg.utilities.adapters.InfiniteFragmentStateAdapter
import tmg.utilities.adapters.InfiniteRecyclerViewAdapter
import kotlin.math.ceil
import kotlin.math.roundToInt

val ViewPager2.recyclerView: RecyclerView
    get() = getChildAt(0) as RecyclerView

/**
 * Set the page width for view pager 2 content
 */
fun ViewPager2.setPageWidth(@Px size: Float) {
    post {
        val paddingSize = (width - (width * size)).roundToInt()/2
        recyclerView.setPadding(paddingSize, 0, paddingSize, 0)
        recyclerView.clipToPadding = false
    }
}

/**
 * Sync scrolling with another viewpager by a specific multiplier
 */
fun ViewPager2.syncScrolling(other: ViewPager2, multiplier: Float = 1f) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            when(state) {
                ViewPager2.SCROLL_STATE_IDLE -> {
                    other.endFakeDrag()
                    other.currentItem = currentItem
                }
                ViewPager2.SCROLL_STATE_DRAGGING -> {
                    other.beginFakeDrag()
                }
                ViewPager2.SCROLL_STATE_SETTLING -> {

                }
            }
        }
    })

    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            other.fakeDragBy(-dx.toFloat()/multiplier)
        }
    })
}

/**
 * Set a desired target width for the fragments shown inside a ViewPager2
 *
 * @param activity
 * @param startPadding Padding at the start of the View Pager
 * @param widthOfContent Width that the padding will be
 *
 * @return Number of items that will be displayed by the viewpager with this target width
 */
fun ViewPager2.setContentWidthForFullWidthPager(activity: Activity, @Px startPadding: Int, @Px widthOfContent: Int): Int {
    val displayMetrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
    val deviceWidth = displayMetrics.widthPixels

    recyclerView.setPadding(startPadding, 0, deviceWidth - widthOfContent - startPadding, 0)
    recyclerView.clipToPadding = false
    recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    return ceil(deviceWidth.toDouble() / widthOfContent.toDouble()).toInt()
}


//region Infinite Fragment / Recyclerview adapter

var ViewPager2.infiniteFragmentAdapter: InfiniteFragmentStateAdapter?
    set(value) {
        adapter = value
    }
    get() = adapter as? InfiniteFragmentStateAdapter

var ViewPager2.infiniteItem: Int
    set(value) {
        currentItem = value + (Integer.MAX_VALUE / 2)
    }
    get() = currentItem - (Integer.MAX_VALUE / 2)

fun ViewPager2.setInfiniteItem(index: Int, smoothScroll: Boolean) {
    setCurrentItem(index + (Integer.MAX_VALUE / 2), smoothScroll)
}

var ViewPager2.infiniteRecyclerViewAdapter: InfiniteRecyclerViewAdapter<*>?
    set(value) {
        adapter = value
    }
    get() = adapter as? InfiniteRecyclerViewAdapter<*>

//endregion
