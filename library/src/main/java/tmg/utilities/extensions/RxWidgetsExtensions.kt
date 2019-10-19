package tmg.utilities.extensions

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.rxjava3.core.Observable
import com.jakewharton.rxbinding3.widget.textChanges

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
        this.setOnFocusChangeListener { view, b ->
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