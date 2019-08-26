[extension-rxjava](../../index.md) / [com.agoda.ninjato.extension.rxjava](../index.md) / [com.agoda.ninjato.Api](./index.md)

### Extensions for com.agoda.ninjato.Api

| Name | Summary |
|---|---|
| [completable](completable.md) | `fun Api.completable(receiver: Api.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): Completable`<br>Wraps the network call with [Completable](#). If no exceptions were thrown, [complete()](#) will be invoked as soon as the call finishes. |
| [observable](observable.md) | `fun <T> Api.observable(receiver: Api.() -> `[`T`](observable.md#T)`): Observable<`[`T`](observable.md#T)`>`<br>Wraps the network call with [Observable](#). If no exceptions were thrown, the inferred return type will be passed to the subscription. |
| [single](single.md) | `fun <T> Api.single(receiver: Api.() -> `[`T`](single.md#T)`): Single<`[`T`](single.md#T)`>`<br>Wraps the network call with [Single](#). If no exceptions were thrown, the inferred return type will be passed to the subscription. |
