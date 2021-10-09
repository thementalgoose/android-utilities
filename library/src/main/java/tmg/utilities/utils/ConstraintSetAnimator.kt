package tmg.utilities.utils

import android.view.animation.DecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager

/**
 * Constraint Set Animator class
 *
 * Example usage:
 *
 * ConstraintSetAnimator(clMain, duration = 200) { newLayout ->
 *    newLayout.setVisibility(R.id.tvText, View.GONE)
 * }
 *
 * Callback should be used to set the other state of the constraint layout
 */
@Deprecated(
    message = "This file has moved package",
    replaceWith = ReplaceWith("tmg.utilities.animation.ConstraintSetAnimator"),
    level = DeprecationLevel.ERROR
)
open class ConstraintSetAnimator(
    private val layout: ConstraintLayout,
    private val duration: Int = 200,
    initialiseNewLayout: (constraintSet: ConstraintSet) -> Unit
) {

    var isAnimated: Boolean = false

    init {
    }

    fun revert() { /* No op */ }

    fun animate() { /* No op */ }

    fun toggle() { /* No op */ }
}