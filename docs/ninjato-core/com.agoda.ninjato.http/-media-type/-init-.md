[ninjato-core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [MediaType](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`MediaType(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, charset: `[`Charset`](http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html)` = Charsets.UTF_8)`

Media type of the [request](../-request/index.md) or [response](../-response/index.md) body encoded in specified
[charset](http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html).

### Parameters

`id` - identifier of the content type. `application/json` as an example.

`charset` - charset of the content. `utf-8` by default.