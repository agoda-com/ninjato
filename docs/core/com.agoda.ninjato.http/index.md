[core](../index.md) / [com.agoda.ninjato.http](./index.md)

## Package com.agoda.ninjato.http

### Types

| Name | Summary |
|---|---|
| [Body](-body/index.md) | `class Body`<br>Body entity of every [request](-request/index.md) and [response](-response/index.md). Stores the actual body value as [byte array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html) but can transform it to [string](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) and [stream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html) |
| [Cookies](-cookies/index.md) | `class Cookies`<br>DSL context for adding cookies to the headers of [request](-request/index.md). Supports addition of a single cookie with it's parameters. |
| [Headers](-headers/index.md) | `class Headers`<br>DSL context for aggregating headers of [request](-request/index.md). Supports additions of single and iterable values of headers mapped to a key. |
| [HttpClient](-http-client/index.md) | `abstract class HttpClient : `[`Commons`](../com.agoda.ninjato.dsl/-commons/index.md)<br>Abstraction of http client. Takes the top level in the cascade of [commons](../com.agoda.ninjato.dsl/-commons/index.md) DSL features. Executes generated requests[Request](-request/index.md) and returns [responses](-response/index.md) in synchronous manner. |
| [MediaType](-media-type/index.md) | `open class MediaType`<br>Media type of the [request](-request/index.md) or [response](-response/index.md) body encoded in specified [charset](http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html). |
| [Method](-method/index.md) | `sealed class Method`<br>Method of the http [request](-request/index.md). |
| [Parameters](-parameters/index.md) | `class Parameters`<br>DSL context for aggregating query parameters of [request](-request/index.md). Supports additions of single value of parameter mapped to a key. |
| [Request](-request/index.md) | `open class Request`<br>Base http entity of the library. Translates into the actual http request by [HttpClient](-http-client/index.md). |
| [Response](-response/index.md) | `open class Response`<br>Base http entity of the library. [HttpClient](-http-client/index.md) translates it's internal response entities into this one and propagates further. |
