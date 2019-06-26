[ninjato-core](../../../../index.md) / [com.agoda.ninjato.http](../../../index.md) / [Request](../../index.md) / [Configurator](../index.md) / [WithBody](./index.md)

# WithBody

`open class WithBody : `[`Request.Configurator`](../index.md)

DSL context for configuring the [Request](../../index.md) with [Body](../../../-body/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `WithBody()`<br>DSL context for configuring the [Request](../../index.md) with [Body](../../../-body/index.md) |

### Properties

| Name | Summary |
|---|---|
| [body](body.md) | `var body: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |

### Inherited Properties

| Name | Summary |
|---|---|
| [converterFactories](../converter-factories.md) | `val converterFactories: `[`ConverterFactories`](../../../../com.agoda.ninjato.converter/-converter-factories/index.md) |
| [endpointUrl](../endpoint-url.md) | `var endpointUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [fallbackPolicy](../fallback-policy.md) | `var fallbackPolicy: `[`FallbackPolicy`](../../../../com.agoda.ninjato.policy/-fallback-policy/index.md)`?` |
| [fullUrl](../full-url.md) | `var fullUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [headers](../headers.md) | `val headers: `[`Headers`](../../../-headers/index.md) |
| [interceptors](../interceptors.md) | `val interceptors: `[`Interceptors`](../../../../com.agoda.ninjato.intercept/-interceptors/index.md) |
| [parameters](../parameters.md) | `val parameters: `[`Parameters`](../../../-parameters/index.md) |
| [retryPolicy](../retry-policy.md) | `var retryPolicy: `[`RetryPolicy`](../../../../com.agoda.ninjato.policy/-retry-policy/index.md)`?` |

### Extension Functions

| Name | Summary |
|---|---|
| [convert](../../../../com.agoda.ninjato.misc/convert.md) | `fun <T> `[`Request.Configurator.WithBody`](./index.md)`.convert(body: `[`T`](../../../../com.agoda.ninjato.misc/convert.md#T)`): `[`Body`](../../../-body/index.md) |
| [formUrlEncoded](../../../../com.agoda.ninjato.misc/form-url-encoded.md) | `fun `[`Request.Configurator.WithBody`](./index.md)`.formUrlEncoded(tail: `[`Parameters`](../../../-parameters/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Body`](../../../-body/index.md) |
