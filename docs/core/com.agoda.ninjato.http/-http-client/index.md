[core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [HttpClient](./index.md)

# HttpClient

`abstract class HttpClient : `[`Commons`](../../com.agoda.ninjato.dsl/-commons/index.md)

Abstraction of http client. Takes the top level in the cascade of
[commons](../../com.agoda.ninjato.dsl/-commons/index.md) DSL features.
Executes generated requests[Request](../-request/index.md) and returns [responses](../-response/index.md) in synchronous manner.

### Parameters

`requestFactory` - factory for building [request](../-request/index.md) entities. `null` by default.

`responseFactory` - factory for building [response](../-response/index.md) entities. `null` by default.

`config` - tail lambda with receiver that allows to configure created instance on the fly.

**See Also**

[Commons](../../com.agoda.ninjato.dsl/-commons/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `HttpClient(requestFactory: `[`Factory`](../-request/-factory/index.md)`? = null, responseFactory: `[`Factory`](../-response/-factory/index.md)`? = null, config: `[`HttpClient`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {})`<br>Abstraction of http client. Takes the top level in the cascade of [commons](../../com.agoda.ninjato.dsl/-commons/index.md) DSL features. Executes generated requests[Request](../-request/index.md) and returns [responses](../-response/index.md) in synchronous manner. |

### Properties

| Name | Summary |
|---|---|
| [converterFactories](converter-factories.md) | `val converterFactories: `[`ConverterFactories`](../../com.agoda.ninjato.converter/-converter-factories/index.md) |
| [fallbackPolicy](fallback-policy.md) | `var fallbackPolicy: `[`FallbackPolicy`](../../com.agoda.ninjato.policy/-fallback-policy/index.md)`?` |
| [headers](headers.md) | `val headers: `[`Headers`](../-headers/index.md) |
| [interceptors](interceptors.md) | `val interceptors: `[`Interceptors`](../../com.agoda.ninjato.intercept/-interceptors/index.md) |
| [parameters](parameters.md) | `val parameters: `[`Parameters`](../-parameters/index.md) |
| [requestFactory](request-factory.md) | `var requestFactory: `[`Factory`](../-request/-factory/index.md)`?`<br>factory for building [request](../-request/index.md) entities. `null` by default. |
| [responseFactory](response-factory.md) | `var responseFactory: `[`Factory`](../-response/-factory/index.md)`?`<br>factory for building [response](../-response/index.md) entities. `null` by default. |
| [retryPolicy](retry-policy.md) | `var retryPolicy: `[`RetryPolicy`](../../com.agoda.ninjato.policy/-retry-policy/index.md)`?` |

### Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | `abstract fun execute(request: `[`Request`](../-request/index.md)`): `[`Response`](../-response/index.md)<br>Executes generated requests[Request](../-request/index.md) and returns [responses](../-response/index.md) in synchronous manner. All thrown exceptions from this function will trigger [retry](../../com.agoda.ninjato.policy/-retry-policy/index.md) and [fallback](../../com.agoda.ninjato.policy/-fallback-policy/index.md) policies. |
| [invoke](invoke.md) | `operator fun invoke(receiver: `[`HttpClient`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [fallbackPolicy](../../com.agoda.ninjato.dsl/-commons/fallback-policy.md) | `open fun fallbackPolicy(policy: (`[`Request`](../-request/index.md)`, `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Request`](../-request/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates anonymous implementation of [FallbackPolicy](../../com.agoda.ninjato.policy/-fallback-policy/index.md) and sets it as current. |
| [retryPolicy](../../com.agoda.ninjato.dsl/-commons/retry-policy.md) | `open fun retryPolicy(policy: (`[`Request`](../-request/index.md)`, `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Retry`](../../com.agoda.ninjato.policy/-retry/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates anonymous implementation of [RetryPolicy](../../com.agoda.ninjato.policy/-retry-policy/index.md) and sets it as current. |
