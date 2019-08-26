[extension-call](../../index.md) / [com.agoda.ninjato.extension.call](../index.md) / [com.agoda.ninjato.Api](index.md) / [call](./call.md)

# call

`inline fun <reified T> Api.call(crossinline receiver: Api.() -> `[`T`](call.md#T)`): `[`Call`](../-call/index.md)`<`[`T`](call.md#T)`>`

Wraps the network call with [Call](../-call/index.md). If no exceptions were thrown, the inferred return type will be
passed as the [result](../-call/-success/result.md) property of [Success](../-call/-success/index.md) wrapper.

If any exception has been raised, you will get [Call.Failure](../-call/-failure/index.md) as result with [throwable](../-call/-failure/throwable.md) property.

### Parameters

`receiver` - tail lambda with the actual API call invocation.