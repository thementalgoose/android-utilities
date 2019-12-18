package tmg.utilities.utils

import io.reactivex.rxjava3.core.Observable

/**
 * Start an observable stream with a value that doesn't close
 * @param method The method called to return the starting value
 */
fun <T> ongoing(method: () -> T): Observable<T> {
    return Observable.create { emitter ->
        emitter.onNext(method())
    }
}