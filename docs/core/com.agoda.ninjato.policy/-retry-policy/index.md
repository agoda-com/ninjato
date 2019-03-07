[core](../../index.md) / [com.agoda.ninjato.policy](../index.md) / [RetryPolicy](./index.md)

# RetryPolicy

`abstract class RetryPolicy : `[`Policy`](../-policy/index.md)`<`[`Retry`](../-retry/index.md)`>`

Retry policy.
This policy gets invoked when request has failed to complete successfully.

The lowest policy present in the DSL cascade ([HttpClient](../../com.agoda.ninjato.http/-http-client/index.md)
-&gt; [Api](../../com.agoda.ninjato/-api/index.md) -&gt; [Request.Configurator](../../com.agoda.ninjato.http/-request/-configurator/index.md)) is selected for invocation.

Retry policies gives the ability to supply custom logic and decide whether you need to retry executing
your requests or not.

**See Also**

[Retry](../-retry/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RetryPolicy()`<br>Retry policy. This policy gets invoked when request has failed to complete successfully. |

### Inherited Functions

| Name | Summary |
|---|---|
| [evaluate](../-policy/evaluate.md) | `abstract fun evaluate(request: `[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`T`](../-policy/index.md#T)<br>Evaluation of the policy. Based on the type, should return different types of resolution. |

### Inheritors

| Name | Summary |
|---|---|
| [DefaultRetryPolicy](../../com.agoda.ninjato.policy.impl/-default-retry-policy/index.md) | `class DefaultRetryPolicy : `[`RetryPolicy`](./index.md)<br>Default implementation of [retry policy](./index.md) bundled with the library. Keep note that this policy is `NOT` used by default and must be manually provided if needed. |
