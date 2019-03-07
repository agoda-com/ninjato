[core](../../index.md) / [com.agoda.ninjato.dsl](../index.md) / [Commons](index.md) / [fallbackPolicy](./fallback-policy.md)

# fallbackPolicy

`abstract var fallbackPolicy: `[`FallbackPolicy`](../../com.agoda.ninjato.policy/-fallback-policy/index.md)`?``open fun fallbackPolicy(policy: (`[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Request`](../../com.agoda.ninjato.http/-request/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Creates anonymous implementation of [FallbackPolicy](../../com.agoda.ninjato.policy/-fallback-policy/index.md) and sets it as current.

### Parameters

`policy` - lambda with fallback policy evaluation.