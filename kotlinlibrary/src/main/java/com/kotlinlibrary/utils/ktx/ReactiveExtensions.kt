package com.kotlinlibrary.utils.ktx

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by gilgoldzweig on 30/08/2017.
 * some small extensions function to allow easier usage with Reactive in android
 */
private val mainThread = AndroidSchedulers.mainThread()
private val newThread = Schedulers.newThread()
private val ioThread = Schedulers.io()
/**
 * observe on main thread
 * subscribe on new thread
 * unsubsidised on error and on complete and removes the need to handle it afterwards
 * @usage
 * someObservable
 *  .runSafeOnMain()
 *  .subscribe({}, {])
 */
fun <T> Observable<T>.runSafeOnMain(): Observable<T> =
    observeOn(mainThread)
        .subscribeOn(newThread)
        .doOnError { unsubscribeOn(newThread) }
        .doOnComplete { unsubscribeOn(newThread) }

fun <T> Observable<T>.runSafeOnIO(): Observable<T> =
    observeOn(ioThread)
        .subscribeOn(newThread)
        .doOnError { unsubscribeOn(newThread) }
        .doOnComplete { unsubscribeOn(newThread) }

fun <T> Observable<T>.smartSubscribe(
    onStart: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    onFinish: (() -> Unit)? = null,
    onSuccess: (T) -> Unit = {}
): Disposable =
    addStartFinishActions(onStart, onFinish)
        .subscribe(onSuccess) { onError?.invoke(it) }

fun <T> Observable<T>.addStartFinishActions(onStart: (() -> Unit)? = null, onFinish: (() -> Unit)? = null): Observable<T> {
    onStart?.invoke()
    return doOnTerminate { onFinish?.invoke() }
}

fun <T> Flowable<T>.runSafeOnMain(): Flowable<T> =
    observeOn(mainThread)
        .subscribeOn(newThread)
        .doOnError { unsubscribeOn(newThread) }
        .doOnComplete { unsubscribeOn(newThread) }

fun <T> Flowable<T>.runSafeOnIO(): Flowable<T> =
    observeOn(ioThread)
        .subscribeOn(newThread)
        .doOnError { unsubscribeOn(newThread) }
        .doOnComplete { unsubscribeOn(newThread) }

fun <T> Flowable<T>.smartSubscribe(
    onStart: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    onFinish: (() -> Unit)? = null,
    onSuccess: (T) -> Unit = {}
): Disposable =
    addStartFinishActions(onStart, onFinish)
        .subscribe(onSuccess) { onError?.invoke(it) }

fun <T> Flowable<T>.addStartFinishActions(onStart: (() -> Unit)? = null, onFinish: (() -> Unit)? = null): Flowable<T> {
    onStart?.invoke()
    return doOnTerminate { onFinish?.invoke() }
}

fun <T> Single<T>.runSafeOnMain(): Single<T> =
    observeOn(mainThread)
        .subscribeOn(newThread)
        .doOnError { unsubscribeOn(newThread) }
        .doOnSuccess { unsubscribeOn(newThread) }

fun <T> Single<T>.runSafeOnIO(): Single<T> =
    observeOn(ioThread)
        .subscribeOn(newThread)
        .doOnError { unsubscribeOn(newThread) }
        .doOnSuccess { unsubscribeOn(newThread) }

fun <T> Single<T>.smartSubscribe(
    onStart: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    onFinish: (() -> Unit)? = null,
    onSuccess: (T) -> Unit = {}
): Disposable =
    addStartFinishActions(onStart, onFinish)
        .subscribe(onSuccess) { onError?.invoke(it) }

fun <T> Single<T>.addStartFinishActions(onStart: (() -> Unit)? = null, onFinish: (() -> Unit)? = null): Single<T> {
    onStart?.invoke()
    return doOnDispose { onFinish?.invoke() }
}

fun Completable.runSafeOnMain(): Completable =
    observeOn(mainThread)
        .subscribeOn(newThread)
        .doOnError { unsubscribeOn(newThread) }
        .doOnComplete { unsubscribeOn(newThread) }

fun Completable.runSafeOnIO(): Completable =
    observeOn(ioThread)
        .subscribeOn(newThread)
        .doOnError { unsubscribeOn(newThread) }
        .doOnComplete { unsubscribeOn(newThread) }

fun Completable.smartSubscribe(
    onStart: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    onFinish: (() -> Unit)? = null,
    onSuccess: () -> Unit = {}
): Disposable =
    addStartFinishActions(onStart, onFinish)
        .subscribe(onSuccess) { onError?.invoke(it) }

fun Completable.addStartFinishActions(onStart: (() -> Unit)? = null, onFinish: (() -> Unit)? = null): Completable {
    onStart?.invoke()
    return doOnDispose { onFinish?.invoke() }
}

fun <T> Maybe<T>.runSafeOnMain(): Maybe<T> =
    observeOn(mainThread)
        .subscribeOn(newThread)
        .doOnError { unsubscribeOn(newThread) }
        .doOnSuccess { unsubscribeOn(newThread) }

fun <T> Maybe<T>.runSafeOnIO(): Maybe<T> =
    observeOn(ioThread)
        .subscribeOn(newThread)
        .doOnError { unsubscribeOn(newThread) }
        .doOnSuccess { unsubscribeOn(newThread) }

fun <T> Maybe<T>.smartSubscribe(
    onStart: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    onFinish: (() -> Unit)? = null,
    onSuccess: (T) -> Unit = {}
): Disposable =
    addStartFinishActions(onStart, onFinish)
        .subscribe(onSuccess) { onError?.invoke(it) }

fun <T> Maybe<T>.addStartFinishActions(onStart: (() -> Unit)? = null, onFinish: (() -> Unit)? = null): Maybe<T> {
    onStart?.invoke()
    return doOnDispose { onFinish?.invoke() }
}
