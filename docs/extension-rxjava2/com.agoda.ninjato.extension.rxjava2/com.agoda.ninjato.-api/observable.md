[extension-rxjava2](../../index.md) / [com.agoda.ninjato.extension.rxjava2](../index.md) / [com.agoda.ninjato.Api](index.md) / [observable](./observable.md)

# observable

`inline fun <reified T> Api.observable(crossinline receiver: Wrapper<`[`T`](observable.md#T)`>.() -> `[`T`](observable.md#T)`): Observable<`[`T`](observable.md#T)`>`

Wraps the network call with [Observable](#). If no exceptions were thrown, the inferred return type will be
passed to the subscription.

### Parameters

`receiver` - tail lambda with the actual API call invocation.