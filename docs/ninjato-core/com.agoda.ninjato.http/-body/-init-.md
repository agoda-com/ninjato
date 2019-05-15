[ninjato-core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [Body](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Body(body: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, mediaType: `[`MediaType`](../-media-type/index.md)` = MediaType.Plain())``Body(body: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`, mediaType: `[`MediaType`](../-media-type/index.md)` = MediaType.Plain())`

Body entity of every [request](../-request/index.md) and [response](../-response/index.md).
Stores the actual body value as [byte array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html) but can transform it to [string](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)
and [stream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html)

### Parameters

`body` - actual body value

`mediaType` - type of the body value and it's [charset](http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html)