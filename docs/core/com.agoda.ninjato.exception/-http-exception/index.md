[core](../../index.md) / [com.agoda.ninjato.exception](../index.md) / [HttpException](./index.md)

# HttpException

`class HttpException : `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)

This exception is thrown when [request](../../com.agoda.ninjato.http/-request/index.md) returns error code and the
inferred (request) return type is not [response](../../com.agoda.ninjato.http/-response/index.md).

Library throws this exception because in case you are requesting not a raw body, any error returned
by server means that response contains [body](../../com.agoda.ninjato.http/-body/index.md) that is already incompatible
with the expected type or not awaited by the user.

### Parameters

`request` - request that has been executed

`response` - response that server has returned

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `HttpException(request: `[`Request`](../../com.agoda.ninjato.http/-request/index.md)`, response: `[`Response`](../../com.agoda.ninjato.http/-response/index.md)`)`<br>This exception is thrown when [request](../../com.agoda.ninjato.http/-request/index.md) returns error code and the inferred (request) return type is not [response](../../com.agoda.ninjato.http/-response/index.md). |

### Properties

| Name | Summary |
|---|---|
| [request](request.md) | `val request: `[`Request`](../../com.agoda.ninjato.http/-request/index.md)<br>request that has been executed |
| [response](response.md) | `val response: `[`Response`](../../com.agoda.ninjato.http/-response/index.md)<br>response that server has returned |
