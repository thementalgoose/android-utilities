package tmg.utilities.lifecycle.rx

import io.reactivex.rxjava3.disposables.CompositeDisposable
import tmg.utilities.lifecycle.common.CommonBottomSheetFragment

/**
 * Rx layer on top of common functionalities to add disposable support
 */
abstract class RxBottomSheetFragment: CommonBottomSheetFragment(), IRxInterface {

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