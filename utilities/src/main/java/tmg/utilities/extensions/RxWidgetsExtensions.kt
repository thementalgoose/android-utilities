package tmg.utilities.extensions

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

//region Buttons

fun Button.textChanged(): Observable<String> {
    return RxTextView
        .textChanges(this)
        .map { it.toString() }
}

//endregion

//region Edit Text

fun EditText.textChanged(): Observable<String> {
    return RxTextView
        .textChanges(this)
        .map { it.toString() }
}

fun EditText.focus(): Observable<Boolean> {
    return RxView
        .focusChanges(this)
}

fun View.click(): Observable<Any> {
    return RxView
        .clicks(this)
}

fun View.clickObservable(): Observable<Unit> {
    return RxView
        .clicks(this)
        .map { Unit }
}

//endregion

//region
fun Switch.click(): Observable<Any> {
    return RxView
        .clicks(this)
}

//endregion