package tmg.utilities.layout

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.IdRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

abstract class AppBarLockedBottomSheetBehavior : AppBarLayout.Behavior {

    abstract val bottomSheetId: Int

    private var mIsSheetTouched = false

    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    )

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout, child: AppBarLayout,
        directTargetChild: View, target: View, axes: Int, type: Int
    ): Boolean {
        // Set flag if the bottom sheet is responsible for the nested scroll.
        mIsSheetTouched = target.id == bottomSheetId

        // Only consider starting a nested scroll if the bottom sheet is not touched; otherwise,
        // we will let the other views do the scrolling.
        return (!mIsSheetTouched
                && super.onStartNestedScroll(
            coordinatorLayout, child, directTargetChild,
            target, axes, type
        ))
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        ev: MotionEvent
    ): Boolean {

        // Don't accept touch stream here if the bottom sheet is touched. This will permit the
        // bottom sheet to be dragged down without interaction with the appBar. Reset on cancel.
        if (ev.actionMasked == MotionEvent.ACTION_CANCEL) {
            mIsSheetTouched = false
        }
        return !mIsSheetTouched && super.onInterceptTouchEvent(parent, child, ev)
    }
}