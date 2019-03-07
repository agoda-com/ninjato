[core](../../index.md) / [com.agoda.ninjato](../index.md) / [Api](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Api(client: `[`HttpClient`](../../com.agoda.ninjato.http/-http-client/index.md)`, config: `[`Api`](index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {})`

Main class of the library.
Provides access to the DSL of call functions.

### Parameters

`client` - http client to forward calls to.

`config` - tail lambda with receiver that allows to configure created instance on the fly.