[core](../../index.md) / [com.agoda.ninjato.intercept](../index.md) / [Interceptor](./index.md)

# Interceptor

`interface Interceptor<T>`

Base interceptor interface that allows custom logic before [request](../../com.agoda.ninjato.http/-request/index.md)
is executed and after [response](../../com.agoda.ninjato.http/-response/index.md) is retrieved.

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `abstract fun intercept(instance: `[`T`](index.md#T)`): `[`T`](index.md#T)<br>Intercepting function. Has the chance to modify the provided value or return a new one of the same type. |

### Inheritors

| Name | Summary |
|---|---|
| [RequestInterceptor](../-request-interceptor/index.md) | `abstract class RequestInterceptor : `[`Interceptor`](./index.md)`<`[`Request`](../../com.agoda.ninjato.http/-request/index.md)`>`<br>Base class for [request](../../com.agoda.ninjato.http/-request/index.md) interceptors. |
| [ResponseInterceptor](../-response-interceptor/index.md) | `abstract class ResponseInterceptor : `[`Interceptor`](./index.md)`<`[`Response`](../../com.agoda.ninjato.http/-response/index.md)`>`<br>Base class for [response](../../com.agoda.ninjato.http/-response/index.md) interceptors. |
