[ninjato-core](../../index.md) / [com.agoda.ninjato.exception](../index.md) / [MissingBodyException](./index.md)

# MissingBodyException

`class MissingBodyException : `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)

This exception is thrown when [method](../../com.agoda.ninjato.http/-method/index.md) of [request](../../com.agoda.ninjato.http/-request/index.md)
requires body to be present but cannot be found by the library.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MissingBodyException(request: `[`Request`](../../com.agoda.ninjato.http/-request/index.md)`)`<br>This exception is thrown when [method](../../com.agoda.ninjato.http/-method/index.md) of [request](../../com.agoda.ninjato.http/-request/index.md) requires body to be present but cannot be found by the library. |

### Properties

| Name | Summary |
|---|---|
| [request](request.md) | `val request: `[`Request`](../../com.agoda.ninjato.http/-request/index.md) |
