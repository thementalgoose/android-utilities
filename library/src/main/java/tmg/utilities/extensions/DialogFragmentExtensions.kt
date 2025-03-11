package tmg.utilities.extensions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.core.graphics.drawable.toDrawable

/**
 * Make the background transparent of the dialog window
 *
 * Should be called in the `onCreateView(inflater, container, savedInstanceState)` method
 */
fun DialogFragment.transparentBackground() {
    dialog?.window?.let {
        it.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        it.requestFeature(Window.FEATURE_NO_TITLE)
    }
}