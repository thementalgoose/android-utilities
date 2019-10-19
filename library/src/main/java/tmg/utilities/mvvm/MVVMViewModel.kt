package tmg.utilities.mvvm

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable

abstract class MVVMViewModel: ViewModel() {

    lateinit var context: Context

    @Deprecated("Please return observables back to the Activity or Fragment. Will be removed in the future")
    private val disposables: MutableList<Disposable> = mutableListOf()

    /**
     * Register a disposable with the view model
     *
     * @deprecated Please pipe disposables out of the View Model into the Activity / Fragment
     */
    @Deprecated("No longer used. Return observables back to the Activity or Fragment")
    fun register(disposable: Disposable) {
        disposables.add(disposable)
    }

    /**
     * Supply the context to the view model before anything gets returned out
     */
    fun supplyContext(context: Context) {
        this.context = context
        contextAvailable()
    }

    open fun contextAvailable() {

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

    /**
     * onCleared()
     */
    override fun onCleared() {
        super.onCleared()
        disposables.forEach {
            it.dispose()
        }
    }
}