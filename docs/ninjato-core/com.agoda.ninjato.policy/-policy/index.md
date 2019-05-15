[ninjato-core](../../index.md) / [com.agoda.ninjato.policy](../index.md) / [Policy](./index.md)

# Policy

`interface Policy<T>`

Base policy interface that allows to apply custom strategies and decisions on retries and fallbacks.

### Functions

| Name | Summary |
|---|---|
| [evaluate](evaluate.md) | `abstract fun evaluate(request: `[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`T`](index.md#T)<br>Evaluation of the policy. Based on the type, should return different types of resolution. |

### Inheritors

| Name | Summary |
|---|---|
| [FallbackPolicy](../-fallback-policy/index.md) | `abstract class FallbackPolicy : `[`Policy`](./index.md)`<`[`Request`](../../com.agoda.ninjato.http/-request/index.md)`>`<br>Fallback policy. This policy gets invoked only if [retry policy](../-retry-policy/index.md)'s resolution was not [DoNotRetry](../-retry/-do-not-retry.md). |
| [RetryPolicy](../-retry-policy/index.md) | `abstract class RetryPolicy : `[`Policy`](./index.md)`<`[`Retry`](../-retry/index.md)`>`<br>Retry policy. This policy gets invoked when request has failed to complete successfully. |
