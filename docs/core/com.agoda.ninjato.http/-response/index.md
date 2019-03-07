[core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [Response](./index.md)

# Response

`open class Response`

Base http entity of the library.
[HttpClient](../-http-client/index.md) translates it's internal response entities into this one and propagates further.

### Types

| Name | Summary |
|---|---|
| [Factory](-factory/index.md) | `abstract class Factory`<br>Factory class for creating instances of request. In case you need to extend [Response](./index.md) and enrich it with some data of your own, provide instance of factory to [HttpClient](../-http-client/index.md). |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Response()`<br>Base http entity of the library. [HttpClient](../-http-client/index.md) translates it's internal response entities into this one and propagates further. |

### Properties

| Name | Summary |
|---|---|
| [body](body.md) | `var body: `[`Body`](../-body/index.md)`?` |
| [code](code.md) | `var code: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [headers](headers.md) | `val headers: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` |
| [isSuccess](is-success.md) | `val isSuccess: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [request](request.md) | `lateinit var request: `[`Request`](../-request/index.md) |
