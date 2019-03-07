[core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [HttpClient](index.md) / [execute](./execute.md)

# execute

`abstract fun execute(request: `[`Request`](../-request/index.md)`): `[`Response`](../-response/index.md)

Executes generated requests[Request](../-request/index.md) and returns [responses](../-response/index.md) in synchronous manner.
All thrown exceptions from this function will trigger [retry](../../com.agoda.ninjato.policy/-retry-policy/index.md) and [fallback](../../com.agoda.ninjato.policy/-fallback-policy/index.md) policies.

### Parameters

`request` - library's request entity

**Return**
library's response entity

