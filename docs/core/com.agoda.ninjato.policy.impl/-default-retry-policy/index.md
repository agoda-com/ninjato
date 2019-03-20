[core](../../index.md) / [com.agoda.ninjato.policy.impl](../index.md) / [DefaultRetryPolicy](./index.md)

# DefaultRetryPolicy

`class DefaultRetryPolicy : `[`RetryPolicy`](../../com.agoda.ninjato.policy/-retry-policy/index.md)

Default implementation of [retry policy](../../com.agoda.ninjato.policy/-retry-policy/index.md) bundled with the library.
Keep note that this policy is `NOT` used by default and must be manually provided if needed.

### Parameters

`retries` - amount of retries that should occur for every failed request regardless of the reason.

`delay` - amount of milliseconds that will be passed to `Thread.sleep()` to perform the delay.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DefaultRetryPolicy(retries: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 3, delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 1000L)`<br>Default implementation of [retry policy](../../com.agoda.ninjato.policy/-retry-policy/index.md) bundled with the library. Keep note that this policy is `NOT` used by default and must be manually provided if needed. |

### Functions

| Name | Summary |
|---|---|
| [evaluate](evaluate.md) | `fun evaluate(request: `[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Retry`](../../com.agoda.ninjato.policy/-retry/index.md)<br>Evaluation of the policy. Based on the type, should return different types of resolution. |
