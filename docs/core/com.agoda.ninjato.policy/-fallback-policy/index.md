[core](../../index.md) / [com.agoda.ninjato.policy](../index.md) / [FallbackPolicy](./index.md)

# FallbackPolicy

`abstract class FallbackPolicy : `[`Policy`](../-policy/index.md)`<`[`Request`](../../com.agoda.ninjato.http/-request/index.md)`>`

Fallback policy.
This policy gets invoked only if [retry policy](../-retry-policy/index.md)'s resolution was not [DoNotRetry](../-retry/-do-not-retry.md).

The lowest policy present in the DSL cascade ([HttpClient](../../com.agoda.ninjato.http/-http-client/index.md)
-&gt; [Api](../../com.agoda.ninjato/-api/index.md) -&gt; [Request.Configurator](../../com.agoda.ninjato.http/-request/-configurator/index.md)) is selected for invocation.

Fallback policies gives the ability to modify your [request](../../com.agoda.ninjato.http/-request/index.md) or provide completely different [request](../../com.agoda.ninjato.http/-request/index.md)
before attempting a retry.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FallbackPolicy()`<br>Fallback policy. This policy gets invoked only if [retry policy](../-retry-policy/index.md)'s resolution was not [DoNotRetry](../-retry/-do-not-retry.md). |

### Inherited Functions

| Name | Summary |
|---|---|
| [evaluate](../-policy/evaluate.md) | `abstract fun evaluate(request: `[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`T`](../-policy/index.md#T)<br>Evaluation of the policy. Based on the type, should return different types of resolution. |
