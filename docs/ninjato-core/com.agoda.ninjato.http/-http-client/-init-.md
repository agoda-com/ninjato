[ninjato-core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [HttpClient](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`HttpClient(requestFactory: `[`Request.Factory`](../-request/-factory/index.md)`? = null, responseFactory: `[`Response.Factory`](../-response/-factory/index.md)`? = null, config: `[`HttpClient`](index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {})`

Abstraction of http client. Takes the top level in the cascade of
[commons](../../com.agoda.ninjato.dsl/-commons/index.md) DSL features.
Executes generated requests[Request](../-request/index.md) and returns [responses](../-response/index.md) in synchronous manner.

### Parameters

`requestFactory` - factory for building [request](../-request/index.md) entities. `null` by default.

`responseFactory` - factory for building [response](../-response/index.md) entities. `null` by default.

`config` - tail lambda with receiver that allows to configure created instance on the fly.

**See Also**

[Commons](../../com.agoda.ninjato.dsl/-commons/index.md)

