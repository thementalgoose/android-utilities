package tmg.utilities.extensions.views

import android.app.Activity
import androidx.annotation.DimenRes
import androidx.annotation.Px
import androidx.appcompat.widget.Toolbar
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import com.google.android.material.appbar.CollapsingToolbarLayout
import tmg.utilities.extensions.statusBarHeight

/**
 * Set the margins for the toolbar that's nested inside a collapsing toolbar layout
 */
fun Toolbar.setMargin(
    @Px left: Int = marginLeft,
    @Px top: Int = marginTop,
    @Px right: Int = marginRight,
    @Px bottom: Int = marginBottom,
    collapsingToolbarMode: Int = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
) {
    val newParams: CollapsingToolbarLayout.LayoutParams = this.layoutParams as CollapsingToolbarLayout.LayoutParams
    newParams.collapseMode = collapsingToolbarMode
    newParams.setMargins(left, top, right, bottom)
    this.layoutParams = newParams
    this.requestLayout()
}


/**
 * Set the margin height of the toolbar to the status bar height
 * Dirty hack - If fitsSystemWindows is not working for the nested layout
 */
fun Toolbar.setPaddingStatusBar(activity: Activity, @DimenRes topPadding: Int) {
    val statusBarHeight = activity.statusBarHeight()
    val totalTop = resources.getDimensionPixelOffset(topPadding) + statusBarHeight
    this.setPadding(this.paddingLeft, totalTop, this.paddingRight, this.paddingBottom)
}