[ninjato-core](../../../index.md) / [com.agoda.ninjato.http](../../index.md) / [MediaType](../index.md) / [JavaScript](./index.md)

# JavaScript

`class JavaScript : `[`MediaType`](../index.md)

Represents the `text/javascript` content type.

### Parameters

`charset` - charset of the content. `utf-8` by default.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `JavaScript(charset: `[`Charset`](https://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html)` = Charsets.UTF_8)`<br>Represents the `text/javascript` content type. |

### Inherited Properties

| Name | Summary |
|---|---|
| [charset](../charset.md) | `val charset: `[`Charset`](https://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html)<br>charset of the content. `utf-8` by default. |
| [id](../id.md) | `val id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>identifier of the content type. `application/json` as an example. |

### Inherited Functions

| Name | Summary |
|---|---|
| [toString](../to-string.md) | `open fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Formats the media type and charset to the `Content-Type` http header's value standards. |
