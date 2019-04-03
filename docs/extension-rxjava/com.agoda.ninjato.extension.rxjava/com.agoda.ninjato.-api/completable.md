[extension-rxjava](../../index.md) / [com.agoda.ninjato.extension.rxjava](../index.md) / [com.agoda.ninjato.Api](index.md) / [completable](./completable.md)

# completable

`inline fun Api.completable(crossinline receiver: Wrapper<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): Completable`

Wraps the network call with [Completable](#). If no exceptions were thrown, [complete()](#)
will be invoked as soon as the call finishes.

### Parameters

`receiver` - tail lambda with the actual API call invocation.