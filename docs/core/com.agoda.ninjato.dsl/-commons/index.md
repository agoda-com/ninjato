[core](../../index.md) / [com.agoda.ninjato.dsl](../index.md) / [Commons](./index.md)

# Commons

`interface Commons`

The main DSL entry of the library.

This interface provides abilities to manipulate [headers](../../com.agoda.ninjato.http/-headers/index.md), [interceptors](../../com.agoda.ninjato.intercept/-interceptors/index.md),
[converter factories](../../com.agoda.ninjato.converter/-converter-factories/index.md), [retry policies](../../com.agoda.ninjato.policy/-retry-policy/index.md) and [fallback policies](../../com.agoda.ninjato.policy/-fallback-policy/index.md).

This interface is reused on 3 different levels:

* [HttpClient](../../com.agoda.ninjato.http/-http-client/index.md)
* [Api](../../com.agoda.ninjato/-api/index.md)
* [Request.Configurator](../../com.agoda.ninjato.http/-request/-configurator/index.md)

This makes the DSL cascade: all the values are aggregated across all three levels to form the final values.

### Properties

| Name | Summary |
|---|---|
| [converterFactories](converter-factories.md) | `abstract val converterFactories: `[`ConverterFactories`](../../com.agoda.ninjato.converter/-converter-factories/index.md) |
| [fallbackPolicy](fallback-policy.md) | `abstract var fallbackPolicy: `[`FallbackPolicy`](../../com.agoda.ninjato.policy/-fallback-policy/index.md)`?` |
| [headers](headers.md) | `abstract val headers: `[`Headers`](../../com.agoda.ninjato.http/-headers/index.md) |
| [interceptors](interceptors.md) | `abstract val interceptors: `[`Interceptors`](../../com.agoda.ninjato.intercept/-interceptors/index.md) |
| [retryPolicy](retry-policy.md) | `abstract var retryPolicy: `[`RetryPolicy`](../../com.agoda.ninjato.policy/-retry-policy/index.md)`?` |

### Functions

| Name | Summary |
|---|---|
| [fallbackPolicy](fallback-policy.md) | `open fun fallbackPolicy(policy: (`[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Request`](../../com.agoda.ninjato.http/-request/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates anonymous implementation of [FallbackPolicy](../../com.agoda.ninjato.policy/-fallback-policy/index.md) and sets it as current. |
| [retryPolicy](retry-policy.md) | `open fun retryPolicy(policy: (`[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Retry`](../../com.agoda.ninjato.policy/-retry/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates anonymous implementation of [RetryPolicy](../../com.agoda.ninjato.policy/-retry-policy/index.md) and sets it as current. |

### Inheritors

| Name | Summary |
|---|---|
| [Api](../../com.agoda.ninjato/-api/index.md) | `abstract class Api : `[`Commons`](./index.md)<br>Main class of the library. Provides access to the DSL of call functions. |
| [Configurator](../../com.agoda.ninjato.http/-request/-configurator/index.md) | `open class Configurator : `[`Commons`](./index.md)<br>DSL context for configuring the [Request](../../com.agoda.ninjato.http/-request/index.md). |
| [HttpClient](../../com.agoda.ninjato.http/-http-client/index.md) | `abstract class HttpClient : `[`Commons`](./index.md)<br>Abstraction of http client. Takes the top level in the cascade of [commons](./index.md) DSL features. Executes generated requests[Request](../../com.agoda.ninjato.http/-request/index.md) and returns [responses](../../com.agoda.ninjato.http/-response/index.md) in synchronous manner. |
