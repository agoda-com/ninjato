[ninjato-core](../../index.md) / [com.agoda.ninjato.intercept](../index.md) / [ResponseInterceptor](./index.md)

# ResponseInterceptor

`abstract class ResponseInterceptor : `[`Interceptor`](../-interceptor/index.md)`<`[`Response`](../../com.agoda.ninjato.http/-response/index.md)`>`

Base class for [response](../../com.agoda.ninjato.http/-response/index.md) interceptors.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ResponseInterceptor()`<br>Base class for [response](../../com.agoda.ninjato.http/-response/index.md) interceptors. |

### Inherited Functions

| Name | Summary |
|---|---|
| [intercept](../-interceptor/intercept.md) | `abstract fun intercept(instance: `[`T`](../-interceptor/index.md#T)`): `[`T`](../-interceptor/index.md#T)<br>Intercepting function. Has the chance to modify the provided value or return a new one of the same type. |
