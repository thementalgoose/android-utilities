package tmg.utilities.lifecycle.rx

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

interface IRxInterface {

    val compositeDisposable: CompositeDisposable

    val disposeOnPause: Boolean
        get() = true

    fun observeViewModel()

    /**
     * Added to end of observable chain to add to [compositeDisposable] associated
     *   with this lifecycle component
     *
     * ```
     * myObservable
     *      .map { it.id }
     *      .subscribe { /* Do something */ }
     *      .autoDispose()
     * ```
     */
    fun Disposable.autoDispose() {
        compositeDisposable.add(this)
    }
}