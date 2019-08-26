[ninjato-core](../index.md) / [com.agoda.ninjato.intercept](./index.md)

## Package com.agoda.ninjato.intercept

### Types

| Name | Summary |
|---|---|
| [Interceptor](-interceptor/index.md) | `interface Interceptor<T>`<br>Base interceptor interface that allows custom logic before [request](../com.agoda.ninjato.http/-request/index.md) is executed and after [response](../com.agoda.ninjato.http/-response/index.md) is retrieved. |
| [Interceptors](-interceptors/index.md) | `class Interceptors`<br>DSL context for aggregating [interceptors](-interceptor/index.md). Supports additions of single and arrays of request and response interceptors. |
| [RequestInterceptor](-request-interceptor/index.md) | `abstract class RequestInterceptor : `[`Interceptor`](-interceptor/index.md)`<`[`Request`](../com.agoda.ninjato.http/-request/index.md)`>`<br>Base class for [request](../com.agoda.ninjato.http/-request/index.md) interceptors. |
| [ResponseInterceptor](-response-interceptor/index.md) | `abstract class ResponseInterceptor : `[`Interceptor`](-interceptor/index.md)`<`[`Response`](../com.agoda.ninjato.http/-response/index.md)`>`<br>Base class for [response](../com.agoda.ninjato.http/-response/index.md) interceptors. |
