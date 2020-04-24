package tmg.utilities.extensions

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import tmg.utilities.bottomsheet.BottomSheetFader

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

//region Bottom sheet

fun <T: View> BottomSheetBehavior<T>.fadeWith(
    background: View,
    id: String = "bottom_sheet",
    callback: ((id: String) -> Unit)? = { }
) {
    addBottomSheetCallback(BottomSheetFader(background, id, callback))
}

//endregion