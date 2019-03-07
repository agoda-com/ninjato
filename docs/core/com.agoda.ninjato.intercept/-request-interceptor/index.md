[core](../../index.md) / [com.agoda.ninjato.intercept](../index.md) / [RequestInterceptor](./index.md)

# RequestInterceptor

`abstract class RequestInterceptor : `[`Interceptor`](../-interceptor/index.md)`<`[`Request`](../../com.agoda.ninjato.http/-request/index.md)`>`

Base class for [request](../../com.agoda.ninjato.http/-request/index.md) interceptors.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RequestInterceptor()`<br>Base class for [request](../../com.agoda.ninjato.http/-request/index.md) interceptors. |

### Inherited Functions

| Name | Summary |
|---|---|
| [intercept](../-interceptor/intercept.md) | `abstract fun intercept(instance: `[`T`](../-interceptor/index.md#T)`): `[`T`](../-interceptor/index.md#T)<br>Intercepting function. Has the chance to modify the provided value or return a new one of the same type. |
