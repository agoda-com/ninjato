[ninjato-core](../../index.md) / [com.agoda.ninjato.extension](../index.md) / [Wrapper](./index.md)

# Wrapper

`abstract class Wrapper<T>`

This class allows contributors to write wrapper-like extension functions.

In current release of Kotlin, the compiler cannot auto-cast the inferred type inside inlined lambdas.

It means that you still have to manually specify the reified type if you're calling such a function inside
a lambda that is being passed to another reified inline function and their types are the same.

Here's the example of usage:

```
inline fun Api.observable(crossinline receiver: Wrapper<T>.() -> T): Observable<T> = Observable.fromCallable {
    receiver(object : Wrapper<T>(this) {})
}

class YourApi : Api {
    fun search(query: String): Observable<SearchResult> = observable {
        get {
            ...
        }
    }
}
```

Without such a wrapper class, you would have to manually specify your type that actually can be inferred:

```
class YourApi : Api {
    fun search(query: String): Observable<SearchResult> = observable {
        get<SearchResult> {
            ...
        }
    }
}
```

### Parameters

`api` - instance of the [Api](../../com.agoda.ninjato/-api/index.md) to wrap around.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Wrapper(api: `[`Api`](../../com.agoda.ninjato/-api/index.md)`)`<br>This class allows contributors to write wrapper-like extension functions. |

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `fun <T> delete(configurator: `[`WithBody`](../../com.agoda.ninjato.http/-request/-configurator/-with-body/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](delete.md#T)<br>Executes the DELETE request with the provided configuration. |
| [get](get.md) | `fun <T> get(configurator: `[`Configurator`](../../com.agoda.ninjato.http/-request/-configurator/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](get.md#T)<br>Executes the GET request with the provided configuration. |
| [head](head.md) | `fun <T> head(configurator: `[`Configurator`](../../com.agoda.ninjato.http/-request/-configurator/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](head.md#T)<br>Executes the HEAD request with the provided configuration. |
| [options](options.md) | `fun <T> options(configurator: `[`WithBody`](../../com.agoda.ninjato.http/-request/-configurator/-with-body/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](options.md#T)<br>Executes the OPTIONS request with the provided configuration. |
| [patch](patch.md) | `fun <T> patch(configurator: `[`WithBody`](../../com.agoda.ninjato.http/-request/-configurator/-with-body/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](patch.md#T)<br>Executes the PATCH request with the provided configuration. |
| [post](post.md) | `fun <T> post(configurator: `[`WithBody`](../../com.agoda.ninjato.http/-request/-configurator/-with-body/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](post.md#T)<br>Executes the POST request with the provided configuration. |
| [put](put.md) | `fun <T> put(configurator: `[`WithBody`](../../com.agoda.ninjato.http/-request/-configurator/-with-body/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](put.md#T)<br>Executes the PUT request with the provided configuration. |
