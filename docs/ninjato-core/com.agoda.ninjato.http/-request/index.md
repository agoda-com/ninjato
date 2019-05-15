[ninjato-core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [Request](./index.md)

# Request

`open class Request`

Base http entity of the library.
Translates into the actual http request by [HttpClient](../-http-client/index.md).

### Types

| Name | Summary |
|---|---|
| [Configurator](-configurator/index.md) | `open class Configurator : `[`Commons`](../../com.agoda.ninjato.dsl/-commons/index.md)<br>DSL context for configuring the [Request](./index.md). |
| [Factory](-factory/index.md) | `abstract class Factory`<br>Factory class for creating instances of request. In case you need to extend [Request](./index.md) and enrich it with some data of your own, provide instance of factory to [HttpClient](../-http-client/index.md). |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Request()`<br>Base http entity of the library. Translates into the actual http request by [HttpClient](../-http-client/index.md). |

### Properties

| Name | Summary |
|---|---|
| [baseUrl](base-url.md) | `var baseUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [body](body.md) | `var body: `[`Body`](../-body/index.md)`?` |
| [endpointUrl](endpoint-url.md) | `var endpointUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [fullUrl](full-url.md) | `var fullUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [headers](headers.md) | `val headers: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` |
| [method](method.md) | `lateinit var method: `[`Method`](../-method/index.md) |
| [parameters](parameters.md) | `val parameters: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [retries](retries.md) | `var retries: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [url](url.md) | `val url: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Represents the actual URL that the server needs to hit. If [fullUrl](full-url.md) property is provided, than this property will return value of [fullUrl](full-url.md) or else will return concatenation of [baseUrl](base-url.md) (provided by [api](../../com.agoda.ninjato/-api/index.md)) and [endpointUrl](endpoint-url.md). |
