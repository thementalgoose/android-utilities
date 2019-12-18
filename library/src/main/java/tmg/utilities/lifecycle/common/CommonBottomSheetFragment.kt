package tmg.utilities.lifecycle.common

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tmg.utilities.R
import tmg.utilities.lifecycle.mvvm.MVVMActivity

/**
 * Common class which includes generic helpers (but no Rx or MVVM logic)
 * See [RxBottomSheetFragment] and [MVVMBottomSheetFragment] for those implementations
 */
abstract class CommonBottomSheetFragment: BottomSheetDialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle: Bundle? = arguments
        bundle?.let {
            if (savedInstanceState != null) {
                it.putAll(savedInstanceState)
            }
        }
        bundle?.let {
            arguments(it)
        }
        super.onCreate(savedInstanceState)
        initComponents()
    }

    /**
     * OnCreateView
     * - Inflate the current view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    /**
     * OnViewCreated
     * - Called when the view is successfully created.
     *   Needs to be here due to Kotlin Layout binding!
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    /**
     * Sets the theme for the fragment to our custom theme
     */
    override fun getTheme(): Int = R.style.Theme_Design_Light_BottomSheetDialog

    /**
     * Creates the dialogue
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    //region Open / Abstract methods

    /**
     * Layout resource id
     */
    @LayoutRes
    abstract fun layoutId(): Int

    /**
     * Argument intercept method. To handle argument imports to the project
     *   Override to import arguments
     */
    open fun arguments(bundle: Bundle) { }

    /**
     * Any component initialisation logic (ie. View Models)
     * Runs before the [initViews] method
     */
    open fun initComponents() { }

    /**
     * Any view initialisation logic
     */
    open fun initViews() { }

    //endregion
}