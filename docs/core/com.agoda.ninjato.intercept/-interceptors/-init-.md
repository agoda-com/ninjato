[core](../../index.md) / [com.agoda.ninjato.intercept](../index.md) / [Interceptors](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Interceptors()`

DSL context for aggregating [interceptors](../-interceptor/index.md).
Supports additions of single and arrays of request and response interceptors.

When resolving all the aggregations across the DSL cascade, keep in mind,
that interceptors that were defined on the lowest level will come first.

