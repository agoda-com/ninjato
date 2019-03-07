package com.agoda.ninjato.extensions.rxjava

import com.agoda.ninjato.Api
import rx.Completable
import rx.Observable
import rx.Single

inline fun <A : Api, reified T> A.completable(crossinline receiver: A.() -> Unit): Completable = Completable.fromCallable { receiver() }

inline fun <A : Api, reified T> A.single(crossinline receiver: A.() -> T): Single<T> = Single.fromCallable { receiver() }

inline fun <A : Api, reified T> A.observable(crossinline receiver: A.() -> T): Observable<T> = Observable.fromCallable { receiver() }
