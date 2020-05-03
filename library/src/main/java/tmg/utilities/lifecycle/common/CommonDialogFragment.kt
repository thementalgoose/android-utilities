package tmg.utilities.lifecycle.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import tmg.utilities.extensions.transparentBackground

/**
 * Common class which includes generic helpers (but no Rx or MVVM logic)
 * See [RxDialogFragment] and [MVVMDialogFragment] for those implementations
 */
abstract class CommonDialogFragment: DialogFragment() {

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
    }

    /**
     * OnCreateView
     * - Inflate the current view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(layoutId(), container, false)
        transparentBackground()
        return v
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
     * Any view initialisation logic
     */
    open fun initViews() { }

    //endregion
}