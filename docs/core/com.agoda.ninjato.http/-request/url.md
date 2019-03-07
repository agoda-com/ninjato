[core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [Request](index.md) / [url](./url.md)

# url

`val url: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Represents the actual URL that the server needs to hit.
If [fullUrl](full-url.md) property is provided, than this property will return value of [fullUrl](full-url.md)
or else will return concatenation of [baseUrl](base-url.md) (provided by [api](../../com.agoda.ninjato/-api/index.md)) and
[endpointUrl](endpoint-url.md).

