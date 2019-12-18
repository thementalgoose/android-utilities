package tmg.utilities.extensions

import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import io.reactivex.rxjava3.core.Notification
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Function3
import tmg.utilities.models.Nullable

/**
 * Calls Observable.combineLatest() on [this] and [other] creating an [Observable] of Pairs of both
 * @return combination [Observable] of Pair<T, O1>.
 */
fun <T, O1> Observable<T>.combineWithPair(other: Observable<O1>):Observable<Pair<T, O1>>{
    return Observable.combineLatest(this, other, CombinePair())
}

/**
 * Calls Observable.zip() on [this] and [other] creating an [Observable] of Pairs of both
 * @return combination [Observable] of Pair<T, O1>.
 */
fun <T, O1> Observable<T>.zipWithPair(other: Observable<O1>):Observable<Pair<T, O1>>{
    return Observable.zip(this, other, CombinePair())
}

/**
 * Calls Observable.zip() on [this] and [other] creating an [Observable] of Pairs of both
 * @return combination [Observable] of Pair<T, O1>.
 */
fun <T, O1, O2> Observable<T>.zipWithTriple(other: Observable<O1>, other2: Observable<O2>):Observable<Triple<T, O1, O2>>{
    return Observable.zip(this, other, other2, CombineTriple())
}

/**
 * Calls Observable.zip() on [this] and [other] creating an [Observable] of Pairs of both
 * @return combination [Observable] of Pair<T, O1>.
 */
fun <T, O1, O2> Observable<T>.combineWithTriple(other: Observable<O1>, other2: Observable<O2>):Observable<Triple<T, O1, O2>>{
    return Observable.combineLatest(this, other, other2, CombineTriple())
}

/**
 * prints a value when onNext is called on the current [Observable] with optional [key] and [transform] of the value
 * @return the original unchanged [Observable]
 */
fun <T> Observable<T>.print(key: String = "it", transform: Function1<T, Any?> = { it }):Observable<T>{
    return doOnNext { println("$key = ${transform.invoke(it)}") }
}
fun <T1, T2, O1> Observable<Pair<T1, T2>>.mapFirst(function: Function1<T1, O1>):Observable<Pair<O1, T2>>{
    return this.map { (t1, t2) ->
        Pair(function(t1), t2)
    }
}
fun <T1, T2, O2> Observable<Pair<T1, T2>>.mapSecond(function: Function1<T2, O2>):Observable<Pair<T1, O2>>{
    return this.map { (t1, t2) ->
        Pair(t1, function(t2))
    }
}

/**
 * applies [function] to each value on the stream, throwing away null transformations
 * @return [Observable] of name [O1]
 */
fun <T, O1> Observable<T>.filterMap(function: Function1<T, O1?>):Observable<O1>{
    return flatMap { it ->
        val result = function.invoke(it)
        if (result != null) Observable.just(result) else Observable.empty()
    }
}

/**
 * converts materialized [Observable] back into regular stream, ignoring errors
 * @return [Observable] of the Values within the [Notification] name
 */
fun <T> Observable<Notification<T>>.removeError():Observable<T>{
    return filterMap { notification ->
        if (notification.isOnNext){
            notification.value
        }
        else{
            null
        }
    }
}

/**
 * Converts materialized [Observable] into a [Observable] of [Throwable]'s
 * @return [Observable] of [Throwable]'s thrown by the original [Observable]
 */
fun <T> Observable<Notification<T>>.toErrorObservable():Observable<Throwable>{
    return filter { it.isOnError }
        .map { it.error }
}

/**
 * Emits the last value on the current stream when [toListen] emits a new item
 * @return [Observable] of [T]
 */
fun <T, O1> Observable<T>.takeWhen(toListen: Observable<O1>):Observable<T>{
    return toListen.withLatestFrom(this, BiFunction<O1, T, T> {_, T1 -> T1})
}

/**
 * Emits the last value on the current stream when [toListen] emits a new item
 * @return [Observable] of [T]
 */
fun <T, O1> Observable<T>.takeIf(other: Observable<O1>, func: (O1) -> Boolean):Observable<T>{
    return this.withLatest(other)
        .filter { func(it.second) }
        .map { it.first }
}

/**
 * Emits the last value on the current stream when either a new value is emitted ot the current stream, or [toListen] emits a new item
 * @return [Observable] of [T]
 */
fun <T, O1> Observable<T>.takeWhenEither(toListen: Observable<O1>):Observable<T>{
    return Observable.combineLatest(this, toListen, BiFunction<T, O1, T> {T1, _ -> T1})
}

/**
 * Calls withLatest on current observable and [other] and combines into a [Pair]
 * @return combination observable of Pair<T, O1>.
 */
fun <T, O1> Observable<T>.withLatest(other: Observable<O1>):Observable<Pair<T, O1>>{
    return this.withLatestFrom(other, CombinePair<T, O1>())
}

/**
 * Calls withLatest on current observable, [other1] and [other2] and combines into a [Triple]
 * @return combination observable of Triple<T, O1, O2>.
 */
fun <T, O1, O2> Observable<T>.withLatest(other1: Observable<O1>, other2: Observable<O2>):Observable<Triple<T, O1, O2>>{
    return this.withLatestFrom(other1, other2, CombineTriple<T, O1, O2>())
}

/**
 * [BiFunction] that takes as input [T1] and [T2] and outputs a [Pair]
 */
class CombinePair<T1, T2>: BiFunction<T1, T2, Pair<T1, T2>>{
    override fun apply(t1: T1, t2: T2): Pair<T1, T2> {
        return Pair(t1, t2)
    }
}

/**
 * [BiFunction] that takes as input [T1], [T2] and [T3] and outputs a [Triple]
 */
class CombineTriple<T1, T2, T3>: Function3<T1, T2, T3, Triple<T1, T2, T3>>{
    override fun apply(t1: T1, t2: T2, t3: T3): Triple<T1, T2, T3> {
        return Triple(t1, t2, t3)
    }
}

/**
 * [BiFunction] that takes as inputs of List<T> and outputs List<T>
 */
class ListConcat<T>: BiFunction<List<T>, List<T>, List<T>>{
    override fun apply(list1: List<T>, list2: List<T>): List<T> {
        return list1 + list2
    }
}


//region Observable View binding

/**
 * Bind the value of the observable to a TextView text
 */
fun Observable<String>.bindText(view: TextView): Disposable {
    return this.subscribe {
        view.text = it
    }
}

/**
 * Bind the value of the observable to a Button text
 */
fun Observable<String>.bindText(view: Button): Disposable {
    return this.subscribe {
        view.text = it
    }
}

/**
 * Bind the value of the observable to an EditText
 */
fun Observable<String>.bindText(view: EditText): Disposable {
    return this.subscribe {
        view.setText(it)
    }
}

/**
 * Bind a drawable value of the observable to an ImageView
 */
fun Observable<Int>.bindResource(view: ImageView): Disposable {
    return this.subscribe {
        view.setImageResource(it)
    }
}

/**
 * Bind the content description to the observable to an ImageView
 */
fun Observable<String>.bindContentDescription(view: ImageView): Disposable {
    return this.subscribe {
        view.contentDescription = it
    }
}

//endregion

/**
 * Print the contents of the observable stream
 */
fun <T> Observable<T>.print(): Observable<T> {
    return this.print { it.toString() }
}

//region Subscription

/**
 * Subscribe to an observable, implement the error handler but do nothing with it
 */
fun <T> Observable<T>.subscribeNoError(onNext: (value: T) -> Unit): Disposable {
    return this.subscribe({
        onNext(it)
    }, { error ->
        Log.e("Rx", "Error occurred but error is suppressed")
        error.printStackTrace()
    })
}

//endregion

//region Observable<Boolean>

/**
 * Check if observable value is true
 */
fun Observable<Boolean>.isTrue(): Observable<Boolean> {
    return this.filter { it }
}

/**
 * Check if observable value is false
 */
fun Observable<Boolean>.isFalse(): Observable<Boolean> {
    return this.filter { !it }
}

//endregion

//region Observable<String>

/**
 * If the string is empty, map it to a given [value]
 */
fun Observable<String>.mapEmptyTo(value: String): Observable<String> {
    return this.map {
        return@map if (it.isEmpty()) {
            value
        }
        else {
            it
        }
    }
}

/**
 * Bind the text to the Edit Text if it's not already in focus!
 */
fun Observable<String>.bindTextIfNotFocus(editText: EditText): Disposable {
    return this.subscribeNoError {
        if (!editText.hasFocus() && editText.text.toString() != it) {
            editText.setText(it)
        }
    }
}

//endregion

//region Observable<List<T>>

/**
 * If the list in this flow is empty then use a specified value
 * @param list
 */
fun <T> Observable<List<T>>.ifListEmpty(list: List<T>): Observable<List<T>> {
    return map {
        return@map if (it.isEmpty()) {
            list
        }
        else {
            it
        }
    }
}

//endregion

//region Observable<Nullable>

/**
 * Filter if the value inside the nullable is actually null or not
 */
fun <T> Observable<Nullable<T>>.filterNotNull(): Observable<T> {
    return concatMap {
        if(it.value != null) {
            Observable.just(it.value)
        }
        else {
            Observable.empty()
        }
    }
}

/**
 * Extract the data held in Nullable<T> and map it's contents to form Nullable<R>
 * @param func
 */
fun <T, R> Observable<Nullable<T>>.mapNullable(func: (T?) -> R?): Observable<Nullable<R>> {
    return map {
        Nullable(func(it.value))
    }
}

/**
 * Wrap a value inside a nullable
 * @param func
 */
fun <T, R> Observable<T>.wrapNullable(func: (T) -> R?): Observable<Nullable<R>> {
    return map {
        Nullable(func(it))
    }
}

/**
 * Switch map operation on a nullable value
 */
fun <T, R> Observable<Nullable<T>>.switchMapNullable(func: (T) -> Observable<R>): Observable<Nullable<R>> {
    return switchMap {
        if(it.value == null) {
            Observable.just(Nullable())
        }
        else {
            func(it.value)
                .map {
                    Nullable(it)
                }
        }
    }
}

//endregion