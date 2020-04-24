package tmg.utilities.lifecycle.mvvm

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import tmg.utilities.lifecycle.rx.RxBottomSheetFragment

/**
 * View Model Specific state restoration for bottom sheet fragments
 *
 * Recommend using RxActivity and something like Koin for Dependency injection, and declare VM
 *   inside each subclass rather than using generics
 */
@Deprecated("No longer supported. Please migrate to not depending on this and use your own dependency management")
abstract class MVVMBottomSheetFragment<VM: MVVMViewModel>: RxBottomSheetFragment() {

    lateinit var viewModel: VM

    override fun initComponents() {
        viewModel = viewModelProvider().get(viewModelClass())
        viewModel.supplyContext(context!!)
    }

    open fun viewModelProvider(): ViewModelProvider {
        return ViewModelProviders.of(this)
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
     * Restore the instance of the VM when the activity is recreated!
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelProvider().get(viewModelClass())
        viewModel.supplyContext(context!!)
    }

    //region Abstract methods

    /**
     * View Model Class
     */
    abstract fun viewModelClass(): Class<VM>

    //endregion
}