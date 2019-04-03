package com.agoda.ninjato.extension.rxjava2

import com.agoda.ninjato.Api
import com.agoda.ninjato.extension.Wrapper
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
inline fun Api.completable(crossinline receiver: Wrapper<Unit>.() -> Unit): Completable = Completable.fromCallable {
    receiver(object : Wrapper<Unit>(this) {})
}

/**
 * Wraps the network call with [Single]. If no exceptions were thrown, the inferred return type will be
 * passed to the subscription.
 *
 * @param receiver tail lambda with the actual API call invocation.
 */
inline fun <reified T> Api.single(crossinline receiver: Wrapper<T>.() -> T): Single<T> = Single.fromCallable {
    receiver(object : Wrapper<T>(this) {})
}

/**
 * Wraps the network call with [Observable]. If no exceptions were thrown, the inferred return type will be
 * passed to the subscription.
 *
 * @param receiver tail lambda with the actual API call invocation.
 */
inline fun <reified T> Api.observable(crossinline receiver: Wrapper<T>.() -> T): Observable<T> = Observable.fromCallable {
    receiver(object : Wrapper<T>(this) {})
}

/**
 * Wraps the network call with [Flowable]. If no exceptions were thrown, the inferred return type will be
 * passed to the subscription.
 *
 * @param receiver tail lambda with the actual API call invocation.
 */
inline fun <reified T> Api.flowable(crossinline receiver: Wrapper<T>.() -> T): Flowable<T> = Flowable.fromCallable {
    receiver(object : Wrapper<T>(this) {})
}

