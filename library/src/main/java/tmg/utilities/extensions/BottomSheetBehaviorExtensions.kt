package tmg.utilities.extensions

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

//region State modifications

fun <T: View> BottomSheetBehavior<T>.collapse() {
    this.state = BottomSheetBehavior.STATE_COLLAPSED
}

fun <T: View> BottomSheetBehavior<T>.expand() {
    this.state = BottomSheetBehavior.STATE_EXPANDED
}

fun <T: View> BottomSheetBehavior<T>.hidden() {
    this.state = BottomSheetBehavior.STATE_HIDDEN
}

//endregion