package tmg.utilities.mvvm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.rxjava3.disposables.Disposable

abstract class MVVMFragment<VM: MVVMViewModel>: Fragment(), MVVMActivityToFragmentCommunicator {

    lateinit var viewModel: VM
    var disposables: MutableList<Disposable> = mutableListOf()

    /**
     * OnCreate Method
     * - Will run arguments(bundle) in the appropriate time
     * - Apply view
     * - Set the VM
     */
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

    /**
     * OnResume
     * - Relaunch Observable bindings
     */
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MVVMFragmentToActivityCommunicator) {
            (context as MVVMFragmentToActivityCommunicator).provideCommunicator(this)
        }
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

    //region Back button behavior

    override fun activityFragmentBackClicked(): Boolean {
        return onBackPressed()
    }

    /**
     * Method called if enabled in [MVVMActivity]
     * Return true if this fragment handles the back, otherwise return false
     * Defaults to false (to handle it back in the activity)
     */
    open fun onBackPressed(): Boolean {
        return false
    }

    //endregion

    //region Abstract methods

    /**
     * Abstract method to get the layout ID.
     *   Will be used in the onCreate to handle layout inflation
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun viewModelClass(): Class<VM>

    open fun viewModelProvider(): ViewModelProvider {
        return ViewModelProviders.of(this)
    }

    /**
     * Observe I/O on the view model
     */
    abstract fun observeViewModel()

    //endregion
}