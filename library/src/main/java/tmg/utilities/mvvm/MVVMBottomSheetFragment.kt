package tmg.utilities.mvvm

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.disposables.Disposable
import tmg.utilities.R

abstract class MVVMBottomSheetFragment<VM: MVVMViewModel>: BottomSheetDialogFragment() {

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
        viewModel.supplyContext(context!!)
    }

    /**
     * OnCreateView
     * - Inflate the current view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
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

    fun viewModelProvider(): ViewModelProvider {
        return ViewModelProviders.of(this)
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
        disposables.forEach { it.dispose() }
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

    /**
     * Sets the theme for the fragment to our custom theme
     */
    override fun getTheme(): Int = R.style.Theme_Design_Light_BottomSheetDialog

    /**
     * Creates the dialogue
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    //region Abstract methods

    /**
     * Abstract method to get the layout ID.
     *   Will be used in the onCreate to handle layout inflation
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun viewModelClass(): Class<VM>

    /**
     * Observe I/O on the view model
     */
    abstract fun observeViewModel()

    //endregion
}