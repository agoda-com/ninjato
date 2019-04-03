package com.agoda.ninjato.extension.rxjava2

import com.agoda.ninjato.Api
import com.agoda.ninjato.extension.Wrapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

inline fun Api.completable(crossinline receiver: Wrapper<Unit>.() -> Unit): Completable = Completable.fromCallable {
    receiver(object : Wrapper<Unit>(this) {})
}

inline fun <reified T> Api.single(crossinline receiver: Wrapper<T>.() -> T): Single<T> = Single.fromCallable {
    receiver(object : Wrapper<T>(this) {})
}

inline fun <reified T> Api.observable(crossinline receiver: Wrapper<T>.() -> T): Observable<T> = Observable.fromCallable {
    receiver(object : Wrapper<T>(this) {})
}

inline fun <reified T> Api.flowable(crossinline receiver: Wrapper<T>.() -> T): Flowable<T> = Flowable.fromCallable {
    receiver(object : Wrapper<T>(this) {})
}

