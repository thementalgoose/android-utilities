package tmg.utilities.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

abstract class BaseViewModel: ViewModel() {

    val disposables: MutableList<Disposable> = mutableListOf()

    /**
     * Register a disposable with the view model
     */
    fun register(disposable: Disposable) {
        disposables.add(disposable)
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