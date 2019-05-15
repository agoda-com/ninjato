[ninjato-core](../../index.md) / [com.agoda.ninjato.policy.impl](../index.md) / [DefaultRetryPolicy](index.md) / [evaluate](./evaluate.md)

# evaluate

`fun evaluate(request: `[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Retry`](../../com.agoda.ninjato.policy/-retry/index.md)

Overrides [Policy.evaluate](../../com.agoda.ninjato.policy/-policy/evaluate.md)

Evaluation of the policy. Based on the type, should return different types of resolution.

### Parameters

`request` - request instance that has failed executing.

`throwable` - error that occurred during request execution.

**Return**
policy's resolution.

