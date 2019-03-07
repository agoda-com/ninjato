[core](../../index.md) / [com.agoda.ninjato](../index.md) / [Api](./index.md)

# Api

`abstract class Api : `[`Commons`](../../com.agoda.ninjato.dsl/-commons/index.md)

Main class of the library.
Provides access to the DSL of call functions.

### Parameters

`client` - http client to forward calls to.

`config` - tail lambda with receiver that allows to configure created instance on the fly.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Api(client: `[`HttpClient`](../../com.agoda.ninjato.http/-http-client/index.md)`, config: `[`Api`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {})`<br>Main class of the library. Provides access to the DSL of call functions. |

### Properties

| Name | Summary |
|---|---|
| [baseUrl](base-url.md) | `abstract val baseUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [converterFactories](converter-factories.md) | `open val converterFactories: `[`ConverterFactories`](../../com.agoda.ninjato.converter/-converter-factories/index.md) |
| [fallbackPolicy](fallback-policy.md) | `open var fallbackPolicy: `[`FallbackPolicy`](../../com.agoda.ninjato.policy/-fallback-policy/index.md)`?` |
| [headers](headers.md) | `open val headers: `[`Headers`](../../com.agoda.ninjato.http/-headers/index.md) |
| [interceptors](interceptors.md) | `open val interceptors: `[`Interceptors`](../../com.agoda.ninjato.intercept/-interceptors/index.md) |
| [retryPolicy](retry-policy.md) | `open var retryPolicy: `[`RetryPolicy`](../../com.agoda.ninjato.policy/-retry-policy/index.md)`?` |

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `fun <T> delete(configurator: `[`WithBody`](../../com.agoda.ninjato.http/-request/-configurator/-with-body/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](delete.md#T)<br>Executes the DELETE request with the provided configuration. |
| [get](get.md) | `fun <T> get(configurator: `[`Configurator`](../../com.agoda.ninjato.http/-request/-configurator/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](get.md#T)<br>Executes the GET request with the provided configuration. |
| [head](head.md) | `fun <T> head(configurator: `[`Configurator`](../../com.agoda.ninjato.http/-request/-configurator/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](head.md#T)<br>Executes the HEAD request with the provided configuration. |
| [invoke](invoke.md) | `operator fun invoke(receiver: `[`Api`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [options](options.md) | `fun <T> options(configurator: `[`WithBody`](../../com.agoda.ninjato.http/-request/-configurator/-with-body/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](options.md#T)<br>Executes the OPTIONS request with the provided configuration. |
| [patch](patch.md) | `fun <T> patch(configurator: `[`WithBody`](../../com.agoda.ninjato.http/-request/-configurator/-with-body/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](patch.md#T)<br>Executes the PATCH request with the provided configuration. |
| [post](post.md) | `fun <T> post(configurator: `[`WithBody`](../../com.agoda.ninjato.http/-request/-configurator/-with-body/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](post.md#T)<br>Executes the POST request with the provided configuration. |
| [put](put.md) | `fun <T> put(configurator: `[`WithBody`](../../com.agoda.ninjato.http/-request/-configurator/-with-body/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](put.md#T)<br>Executes the PUT request with the provided configuration. |

### Inherited Functions

| Name | Summary |
|---|---|
| [fallbackPolicy](../../com.agoda.ninjato.dsl/-commons/fallback-policy.md) | `open fun fallbackPolicy(policy: (`[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Request`](../../com.agoda.ninjato.http/-request/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates anonymous implementation of [FallbackPolicy](../../com.agoda.ninjato.policy/-fallback-policy/index.md) and sets it as current. |
| [retryPolicy](../../com.agoda.ninjato.dsl/-commons/retry-policy.md) | `open fun retryPolicy(policy: (`[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Retry`](../../com.agoda.ninjato.policy/-retry/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates anonymous implementation of [RetryPolicy](../../com.agoda.ninjato.policy/-retry-policy/index.md) and sets it as current. |
