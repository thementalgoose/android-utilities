package tmg.utilities.lifecycle.mvvm

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tmg.utilities.lifecycle.rx.RxActivity

/**
 * View Model Specific state restoration for activities
 *
 * Recommend using RxActivity and something like Koin for Dependency injection, and declare VM
 *   inside each subclass rather than using generics
 */
abstract class MVVMActivity<VM: MVVMViewModel>: RxActivity() {

    lateinit var viewModel: VM

    override fun initComponents() {
        viewModel = viewModelProvider().get(viewModelClass())
        viewModel.supplyContext(applicationContext)
    }

    /**
     * Provider for finding VM inflation
     */
    open fun viewModelProvider(): ViewModelProvider {
        return ViewModelProviders.of(this)
    }

    /**
     * State restoration methods for passing data to the view models
     * - onSaveInstanceState
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.let {
            viewModel.saveInstanceState(it)
        }
        super.onSaveInstanceState(outState)
    }

    /**
     * State restoration methods for passing data to the view models
     * - onRestoreInstanceState
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.let {
            viewModel.restoreInstanceState(it)
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

    //region Open / Abstract methods

    /**
     * Get the class of the VM for VM inflation
     */
    abstract fun viewModelClass(): Class<VM>

    //endregion
}