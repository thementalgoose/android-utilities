package tmg.utilities.lifecycle.rx

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import tmg.utilities.lifecycle.common.CommonFragment

/**
 * Rx layer on top of common functionalities to add disposable support
 */
abstract class RxFragment: CommonFragment(), IRxInterface {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * OnResume - Resubscribe to all disposables
     */
    override fun onResume() {
        super.onResume()
        observeViewModel()
    }

    /**
     * onPause - Clear disposables
     * Can override [disposeOnPause] if you wish to alter this behavior
     */
    override fun onPause() {
        super.onPause()
        if (disposeOnPause) {
            compositeDisposable.clear()
        }
    }
}