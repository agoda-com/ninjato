[ninjato-core](../../../index.md) / [com.agoda.ninjato.http](../../index.md) / [Response](../index.md) / [Factory](./index.md)

# Factory

`abstract class Factory`

Factory class for creating instances of request.
In case you need to extend [Response](../index.md) and enrich it with some data of your own,
provide instance of factory to [HttpClient](../../-http-client/index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Factory()`<br>Factory class for creating instances of request. In case you need to extend [Response](../index.md) and enrich it with some data of your own, provide instance of factory to [HttpClient](../../-http-client/index.md). |

### Functions

| Name | Summary |
|---|---|
| [create](create.md) | `abstract fun create(): `[`Response`](../index.md) |
