[ninjato-core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [MediaType](./index.md)

# MediaType

`open class MediaType`

Media type of the [request](../-request/index.md) or [response](../-response/index.md) body encoded in specified
[charset](https://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html).

### Parameters

`id` - identifier of the content type. `application/json` as an example.

`charset` - charset of the content. `utf-8` by default.

### Types

| Name | Summary |
|---|---|
| [Css](-css/index.md) | `class Css : `[`MediaType`](./index.md)<br>Represents the `text/css` content type. |
| [FormUrlEncoded](-form-url-encoded/index.md) | `class FormUrlEncoded : `[`MediaType`](./index.md)<br>Represents the `application/x-www-form-urlencoded` content type. |
| [Html](-html/index.md) | `class Html : `[`MediaType`](./index.md)<br>Represents the `text/html` content type. |
| [JavaScript](-java-script/index.md) | `class JavaScript : `[`MediaType`](./index.md)<br>Represents the `text/javascript` content type. |
| [Json](-json/index.md) | `class Json : `[`MediaType`](./index.md)<br>Represents the `application/json` content type. |
| [Plain](-plain/index.md) | `class Plain : `[`MediaType`](./index.md)<br>Represents the `text/plain` content type. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MediaType(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, charset: `[`Charset`](https://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html)` = Charsets.UTF_8)`<br>Media type of the [request](../-request/index.md) or [response](../-response/index.md) body encoded in specified [charset](https://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html). |

### Properties

| Name | Summary |
|---|---|
| [charset](charset.md) | `val charset: `[`Charset`](https://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html)<br>charset of the content. `utf-8` by default. |
| [id](id.md) | `val id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>identifier of the content type. `application/json` as an example. |

### Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | `open fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Formats the media type and charset to the `Content-Type` http header's value standards. |

### Inheritors

| Name | Summary |
|---|---|
| [Css](-css/index.md) | `class Css : `[`MediaType`](./index.md)<br>Represents the `text/css` content type. |
| [FormUrlEncoded](-form-url-encoded/index.md) | `class FormUrlEncoded : `[`MediaType`](./index.md)<br>Represents the `application/x-www-form-urlencoded` content type. |
| [Html](-html/index.md) | `class Html : `[`MediaType`](./index.md)<br>Represents the `text/html` content type. |
| [JavaScript](-java-script/index.md) | `class JavaScript : `[`MediaType`](./index.md)<br>Represents the `text/javascript` content type. |
| [Json](-json/index.md) | `class Json : `[`MediaType`](./index.md)<br>Represents the `application/json` content type. |
| [Plain](-plain/index.md) | `class Plain : `[`MediaType`](./index.md)<br>Represents the `text/plain` content type. |
