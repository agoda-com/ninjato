[ninjato-core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [Method](index.md) / [Get](./-get.md)

# Get

`object Get : `[`Method`](index.md)

Represents the `GET` http method. [Body](../-body/index.md) is not required.

### Inherited Properties

| Name | Summary |
|---|---|
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>name of the method |
| [requiresBody](requires-body.md) | `val requiresBody: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>if set to `true`, library will check that the [body](../-body/index.md) for [requests](../-request/index.md) is provided and will throw [exception](../../com.agoda.ninjato.exception/-missing-body-exception/index.md) if it is not found. |
