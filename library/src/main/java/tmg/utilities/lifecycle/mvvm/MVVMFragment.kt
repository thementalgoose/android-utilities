package tmg.utilities.lifecycle.mvvm

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import tmg.utilities.lifecycle.rx.RxFragment

/**
 * View Model Specific state restoration for fragments
 *
 * Recommend using RxActivity and something like Koin for Dependency injection, and declare VM
 *   inside each subclass rather than using generics
 */
@Deprecated("No longer supported. Please migrate to not depending on this and use your own dependency management, such as Dagger / Koin. This will be removed as of 2.0.0", level = DeprecationLevel.WARNING)
abstract class MVVMFragment<VM: MVVMViewModel>: RxFragment() {

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

    abstract fun viewModelClass(): Class<VM>

    //endregion
}