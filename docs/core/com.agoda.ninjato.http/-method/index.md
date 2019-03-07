[core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [Method](./index.md)

# Method

`sealed class Method`

Method of the http [request](../-request/index.md).

### Parameters

`name` - name of the method

`requiresBody` - if set to `true`, library will check that the [body](../-body/index.md) for [requests](../-request/index.md)
is provided and will throw [exception](../../com.agoda.ninjato.exception/-missing-body-exception/index.md) if it is not found.

### Types

| Name | Summary |
|---|---|
| [Delete](-delete.md) | `object Delete : `[`Method`](./index.md)<br>Represents the `DELETE` http method. [Body](../-body/index.md) is not required. |
| [Get](-get.md) | `object Get : `[`Method`](./index.md)<br>Represents the `GET` http method. [Body](../-body/index.md) is not required. |
| [Head](-head.md) | `object Head : `[`Method`](./index.md)<br>Represents the `HEAD` http method. [Body](../-body/index.md) is not required. |
| [Options](-options.md) | `object Options : `[`Method`](./index.md)<br>Represents the `OPTIONS` http method. [Body](../-body/index.md) is not required. |
| [Patch](-patch.md) | `object Patch : `[`Method`](./index.md)<br>Represents the `PATCH` http method. [Body](../-body/index.md) is required. |
| [Post](-post.md) | `object Post : `[`Method`](./index.md)<br>Represents the `POST` http method. [Body](../-body/index.md) is required. |
| [Put](-put.md) | `object Put : `[`Method`](./index.md)<br>Represents the `PUT` http method. [Body](../-body/index.md) is required. |

### Properties

| Name | Summary |
|---|---|
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>name of the method |
| [requiresBody](requires-body.md) | `val requiresBody: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>if set to `true`, library will check that the [body](../-body/index.md) for [requests](../-request/index.md) is provided and will throw [exception](../../com.agoda.ninjato.exception/-missing-body-exception/index.md) if it is not found. |

### Inheritors

| Name | Summary |
|---|---|
| [Delete](-delete.md) | `object Delete : `[`Method`](./index.md)<br>Represents the `DELETE` http method. [Body](../-body/index.md) is not required. |
| [Get](-get.md) | `object Get : `[`Method`](./index.md)<br>Represents the `GET` http method. [Body](../-body/index.md) is not required. |
| [Head](-head.md) | `object Head : `[`Method`](./index.md)<br>Represents the `HEAD` http method. [Body](../-body/index.md) is not required. |
| [Options](-options.md) | `object Options : `[`Method`](./index.md)<br>Represents the `OPTIONS` http method. [Body](../-body/index.md) is not required. |
| [Patch](-patch.md) | `object Patch : `[`Method`](./index.md)<br>Represents the `PATCH` http method. [Body](../-body/index.md) is required. |
| [Post](-post.md) | `object Post : `[`Method`](./index.md)<br>Represents the `POST` http method. [Body](../-body/index.md) is required. |
| [Put](-put.md) | `object Put : `[`Method`](./index.md)<br>Represents the `PUT` http method. [Body](../-body/index.md) is required. |
