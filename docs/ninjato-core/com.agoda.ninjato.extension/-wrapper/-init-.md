[ninjato-core](../../index.md) / [com.agoda.ninjato.extension](../index.md) / [Wrapper](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Wrapper(api: `[`Api`](../../com.agoda.ninjato/-api/index.md)`)`

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