[ninjato-core](../../index.md) / [com.agoda.ninjato.dsl](../index.md) / [Commons](index.md) / [retryPolicy](./retry-policy.md)

# retryPolicy

`abstract var retryPolicy: `[`RetryPolicy`](../../com.agoda.ninjato.policy/-retry-policy/index.md)`?``open fun retryPolicy(policy: (`[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Retry`](../../com.agoda.ninjato.policy/-retry/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Creates anonymous implementation of [RetryPolicy](../../com.agoda.ninjato.policy/-retry-policy/index.md) and sets it as current.

### Parameters

`policy` - lambda with retry policy evaluation.