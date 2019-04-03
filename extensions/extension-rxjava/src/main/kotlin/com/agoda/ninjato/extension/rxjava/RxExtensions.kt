package com.agoda.ninjato.extension.rxjava

import com.agoda.ninjato.Api
import com.agoda.ninjato.extension.Wrapper
import rx.Completable
import rx.Observable
import rx.Single

inline fun Api.completable(crossinline receiver: Wrapper<Unit>.() -> Unit): Completable = Completable.fromCallable {
    receiver(object : Wrapper<Unit>(this) {})
}

inline fun <reified T> Api.single(crossinline receiver: Wrapper<T>.() -> T): Single<T> = Single.fromCallable {
    receiver(object : Wrapper<T>(this) {})
}

inline fun <reified T> Api.observable(crossinline receiver: Wrapper<T>.() -> T): Observable<T> = Observable.fromCallable {
    receiver(object : Wrapper<T>(this) {})
}
