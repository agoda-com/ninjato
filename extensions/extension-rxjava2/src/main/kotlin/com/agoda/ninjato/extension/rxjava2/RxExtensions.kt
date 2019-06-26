package com.agoda.ninjato.extension.rxjava2

import com.agoda.ninjato.Api
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Wraps the network call with [Completable]. If no exceptions were thrown, [complete()][Completable.complete]
 * will be invoked as soon as the call finishes.
 *
 * @param receiver tail lambda with the actual API call invocation.
 */
inline fun Api.completable(crossinline receiver: Api.() -> Unit): Completable = Completable.fromCallable {
    receiver(this)
}

/**
 * Wraps the network call with [Single]. If no exceptions were thrown, the inferred return type will be
 * passed to the subscription.
 *
 * @param receiver tail lambda with the actual API call invocation.
 */
inline fun <reified T> Api.single(crossinline receiver: Api.() -> T): Single<T> = Single.fromCallable {
    receiver(this)
}

/**
 * Wraps the network call with [Observable]. If no exceptions were thrown, the inferred return type will be
 * passed to the subscription.
 *
 * @param receiver tail lambda with the actual API call invocation.
 */
inline fun <reified T> Api.observable(crossinline receiver: Api.() -> T): Observable<T> = Observable.fromCallable {
    receiver(this)
}

/**
 * Wraps the network call with [Flowable]. If no exceptions were thrown, the inferred return type will be
 * passed to the subscription.
 *
 * @param receiver tail lambda with the actual API call invocation.
 */
inline fun <reified T> Api.flowable(crossinline receiver: Api.() -> T): Flowable<T> = Flowable.fromCallable {
    receiver(this)
}

