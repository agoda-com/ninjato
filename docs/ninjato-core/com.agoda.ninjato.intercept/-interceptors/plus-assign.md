[ninjato-core](../../index.md) / [com.agoda.ninjato.intercept](../index.md) / [Interceptors](index.md) / [plusAssign](./plus-assign.md)

# plusAssign

`operator fun plusAssign(interceptor: `[`RequestInterceptor`](../-request-interceptor/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Adds provided request interceptor to the aggregation.

### Parameters

`interceptor` - request interceptor to add.`operator fun plusAssign(interceptors: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`RequestInterceptor`](../-request-interceptor/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Adds provided request interceptors to the aggregation.

### Parameters

`interceptors` - request interceptors to add.`operator fun plusAssign(interceptor: `[`ResponseInterceptor`](../-response-interceptor/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Adds provided response interceptor to the aggregation.

### Parameters

`interceptor` - response interceptor to add.`operator fun plusAssign(interceptors: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`ResponseInterceptor`](../-response-interceptor/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Adds provided response interceptors to the aggregation.

### Parameters

`interceptors` - response interceptors to add.