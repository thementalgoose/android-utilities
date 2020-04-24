package tmg.utilities.extensions

import android.view.View
import android.widget.EditText
import android.widget.Switch
import androidx.core.widget.addTextChangedListener
import io.reactivex.rxjava3.core.Observable

// TODO: Replace these with proper listeners when RxBindings gets updated to support RxJava3

//region Edit Text

fun EditText.textChanged(): Observable<String> {
    return Observable.create { emitter ->
        this.addTextChangedListener {
            emitter.onNext(it.toString())
        }
    }
}

fun EditText.focus(): Observable<Boolean> {
    return Observable.create { emitter ->
        this.setOnFocusChangeListener { _, b ->
            emitter.onNext(b)
        }
    }
}

fun View.click(): Observable<Any> {
    return Observable.create { emitter ->
        this.setOnClickListener {
            emitter.onNext(Unit)
        }
    }
}

//endregion

//region Switch

fun Switch.click(): Observable<Any> {
    return Observable.create { emitter ->
        this.setOnClickListener {
            emitter.onNext(Unit)
        }
    }
}

//endregion