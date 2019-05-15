[ninjato-core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [Body](./index.md)

# Body

`class Body`

Body entity of every [request](../-request/index.md) and [response](../-response/index.md).
Stores the actual body value as [byte array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html) but can transform it to [string](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)
and [stream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html)

### Parameters

`body` - actual body value

`mediaType` - type of the body value and it's [charset](http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Body(body: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, mediaType: `[`MediaType`](../-media-type/index.md)` = MediaType.Plain())``Body(body: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`, mediaType: `[`MediaType`](../-media-type/index.md)` = MediaType.Plain())`<br>Body entity of every [request](../-request/index.md) and [response](../-response/index.md). Stores the actual body value as [byte array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html) but can transform it to [string](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) and [stream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html) |

### Properties

| Name | Summary |
|---|---|
| [asByteArray](as-byte-array.md) | `val asByteArray: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)<br>Represents the body's value as [byte array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html) |
| [asStream](as-stream.md) | `val asStream: `[`InputStream`](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html)<br>Represents the body's value as [input stream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html) |
| [asString](as-string.md) | `val asString: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Represents the body's value as [string](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) with media type's [charset](http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html) |
| [contentLength](content-length.md) | `val contentLength: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Amount of bytes in the body's value |
| [mediaType](media-type.md) | `val mediaType: `[`MediaType`](../-media-type/index.md)<br>type of the body value and it's [charset](http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html) |
