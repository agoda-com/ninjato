[ninjato-core](../../../index.md) / [com.agoda.ninjato.http](../../index.md) / [Request](../index.md) / [Configurator](./index.md)

# Configurator

`open class Configurator : `[`Commons`](../../../com.agoda.ninjato.dsl/-commons/index.md)

DSL context for configuring the [Request](../index.md).

### Types

| Name | Summary |
|---|---|
| [WithBody](-with-body/index.md) | `open class WithBody : `[`Configurator`](./index.md)<br>DSL context for configuring the [Request](../index.md) with [Body](../../-body/index.md) |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Configurator()`<br>DSL context for configuring the [Request](../index.md). |

### Properties

| Name | Summary |
|---|---|
| [converterFactories](converter-factories.md) | `val converterFactories: `[`ConverterFactories`](../../../com.agoda.ninjato.converter/-converter-factories/index.md) |
| [endpointUrl](endpoint-url.md) | `var endpointUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [fallbackPolicy](fallback-policy.md) | `var fallbackPolicy: `[`FallbackPolicy`](../../../com.agoda.ninjato.policy/-fallback-policy/index.md)`?` |
| [fullUrl](full-url.md) | `var fullUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [headers](headers.md) | `val headers: `[`Headers`](../../-headers/index.md) |
| [interceptors](interceptors.md) | `val interceptors: `[`Interceptors`](../../../com.agoda.ninjato.intercept/-interceptors/index.md) |
| [parameters](parameters.md) | `val parameters: `[`Parameters`](../../-parameters/index.md) |
| [retryPolicy](retry-policy.md) | `var retryPolicy: `[`RetryPolicy`](../../../com.agoda.ninjato.policy/-retry-policy/index.md)`?` |

### Inherited Functions

| Name | Summary |
|---|---|
| [fallbackPolicy](../../../com.agoda.ninjato.dsl/-commons/fallback-policy.md) | `open fun fallbackPolicy(policy: (`[`Request`](../index.md)`, `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Request`](../index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates anonymous implementation of [FallbackPolicy](../../../com.agoda.ninjato.policy/-fallback-policy/index.md) and sets it as current. |
| [retryPolicy](../../../com.agoda.ninjato.dsl/-commons/retry-policy.md) | `open fun retryPolicy(policy: (`[`Request`](../index.md)`, `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Retry`](../../../com.agoda.ninjato.policy/-retry/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates anonymous implementation of [RetryPolicy](../../../com.agoda.ninjato.policy/-retry-policy/index.md) and sets it as current. |

### Inheritors

| Name | Summary |
|---|---|
| [WithBody](-with-body/index.md) | `open class WithBody : `[`Configurator`](./index.md)<br>DSL context for configuring the [Request](../index.md) with [Body](../../-body/index.md) |
