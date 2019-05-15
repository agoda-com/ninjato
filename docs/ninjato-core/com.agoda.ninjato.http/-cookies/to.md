[ninjato-core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [Cookies](index.md) / [to](./to.md)

# to

`infix fun `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.to(value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Sets the name and value of the cookie.

IMPORTANT: this invocation is mandatory and should only happen once.

### Parameters

`value` - value of a cookie.

### Exceptions

`IllegalStateException` - if called more that once in the same context.