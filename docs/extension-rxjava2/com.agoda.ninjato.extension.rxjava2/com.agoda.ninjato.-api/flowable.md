[extension-rxjava2](../../index.md) / [com.agoda.ninjato.extension.rxjava2](../index.md) / [com.agoda.ninjato.Api](index.md) / [flowable](./flowable.md)

# flowable

`inline fun <reified T> Api.flowable(crossinline receiver: Wrapper<`[`T`](flowable.md#T)`>.() -> `[`T`](flowable.md#T)`): Flowable<`[`T`](flowable.md#T)`>`

Wraps the network call with [Flowable](#). If no exceptions were thrown, the inferred return type will be
passed to the subscription.

### Parameters

`receiver` - tail lambda with the actual API call invocation.