package tmg.utilities.mvvm

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.disposables.Disposable


abstract class MVVMDialogFragment<VM: MVVMViewModel>: DialogFragment() {
    lateinit var viewModel: VM
    var disposables: MutableList<Disposable> = mutableListOf()
    /**
     * OnCreate Method
     * - Will run arguments(bundle) in the appropriate time
     * - Apply view
     * - Set the VM
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            arguments(it)
        }
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider().get(viewModelClass())
    }

    abstract fun viewModelClass(): Class<VM>

    open fun viewModelProvider(): ViewModelProvider {
        return ViewModelProviders.of(this)
    }

    /**
     * OnCreateView
     * - Inflate the current view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(getLayoutId(), container, false)
        if (dialog != null && dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
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
    /**
     * Argument intercept method. To handle argument imports to the project
     *   Override to import arguments
     */
    open fun arguments(bundle: Bundle) {
    }
    /**
     * Initialise the views
     *   Override to perform some operations on the view
     */
    open fun initViews() {
    }
    override fun onResume() {
        super.onResume()
        observeViewModel()
    }
    /**
     * OnPause method
     * - Clean up observables
     */
    override fun onPause() {
        super.onPause()
        if (disposeOnPause()) {
            disposables.forEach { it.dispose() }
        }
    }
    open fun disposeOnPause(): Boolean {
        return true
    }
    /**
     * State restoration methods for passing data to the view models
     * - onSaveInstanceState
     */
    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }
    /**
     * State restoration methods for passing data to the view models
     * - onRestoreInstanceState
     */
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            viewModel.restoreInstanceState(it)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelProvider().get(viewModelClass())
    }
    //region Abstract methods
    /**
     * Abstract method to get the layout ID.
     *   Will be used in the onCreate to handle layout inflation
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Observe I/O on the view model
     */
    abstract fun observeViewModel()
    //endregion
}