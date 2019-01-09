package tmg.utilities.extensions

import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.subjects.PublishSubject

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

class ListConcat<T>: BiFunction<List<T>, List<T>, List<T>>{
    override fun apply(list1: List<T>, list2: List<T>): List<T> {
        return list1 + list2
    }
}


/**
 * Unwrapping errors
 */
fun <T: Throwable,E> Observable<E>.forwardErrorsTo(publishSubject: PublishSubject<T>): Observable<E> {
    return doOnError {
        publishSubject.onNext(it as T)
    }
}

/**
 *
 */
fun <T> combineFlatten(items: List<Observable<List<T>>>): Observable<List<T>> {
    return Observable.combineLatest(items) {list ->
        list.map { it as List<T> }.toList().flatten()
    }
}

fun <T> Observable<T>.subscribeNoError(subscribe: (T) -> Unit): Disposable {
    return subscribe(subscribe, {})
}

fun <T> Observable<List<T>>.scanConcat(): Observable<List<T>> {
    return scan(ListConcat())
}
fun Observable<Int>.scanSum(): Observable<Int> {
    return scan(IntSum())
}

class IntSum : BiFunction<Int, Int, Int> {
    override fun apply(t1: Int, t2: Int): Int {
        return t1 + t2
    }
}