[ninjato-core](../../index.md) / [com.agoda.ninjato.intercept](../index.md) / [Interceptors](./index.md)

# Interceptors

`class Interceptors`

DSL context for aggregating [interceptors](../-interceptor/index.md).
Supports additions of single and arrays of request and response interceptors.

When resolving all the aggregations across the DSL cascade, keep in mind,
that interceptors that were defined on the lowest level will come first.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Interceptors()`<br>DSL context for aggregating [interceptors](../-interceptor/index.md). Supports additions of single and arrays of request and response interceptors. |

### Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | `operator fun invoke(receiver: `[`Interceptors`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [plusAssign](plus-assign.md) | `operator fun plusAssign(interceptor: `[`RequestInterceptor`](../-request-interceptor/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds provided request interceptor to the aggregation.`operator fun plusAssign(interceptors: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`RequestInterceptor`](../-request-interceptor/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds provided request interceptors to the aggregation.`operator fun plusAssign(interceptor: `[`ResponseInterceptor`](../-response-interceptor/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds provided response interceptor to the aggregation.`operator fun plusAssign(interceptors: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`ResponseInterceptor`](../-response-interceptor/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds provided response interceptors to the aggregation. |
| [request](request.md) | `fun request(interceptor: (`[`Request`](../../com.agoda.ninjato.http/-request/index.md)`) -> `[`Request`](../../com.agoda.ninjato.http/-request/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds anonymous object of request interceptor to the aggregation. |
| [response](response.md) | `fun response(interceptor: (`[`Response`](../../com.agoda.ninjato.http/-response/index.md)`) -> `[`Response`](../../com.agoda.ninjato.http/-response/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds anonymous object of response interceptor to the aggregation. |
