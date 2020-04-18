package tmg.utilities.lifecycle.mvvm

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel

@Deprecated("No longer supported. Please use ViewModel as a raw class and generate your own base model")
abstract class MVVMViewModel: ViewModel() {

    lateinit var context: Context

    /**
     * Supply the context to the view model before anything gets returned out
     */
    fun supplyContext(context: Context) {
        this.context = context
        contextAvailable(this.context)
    }

    /**
     * Overridable method which is fired when context is initialised
     */
    open fun contextAvailable(context: Context) {

    }

    /**
     * State handling method for state restoration
     */
    open fun saveInstanceState(bundle: Bundle) {

    }

    /**
     * State handling method for state restoration
     */
    open fun restoreInstanceState(bundle: Bundle) {

    }
}