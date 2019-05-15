[ninjato-core](../../index.md) / [com.agoda.ninjato.converter](../index.md) / [ConverterFactories](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`ConverterFactories()`

DSL context for aggregating [converter factories](../-body-converter/-factory/index.md).
Supports additions of single factory and iterables of factories.

When resolving all the aggregations across the DSL cascade, keep in mind,
that factories that were defined on the lowest level will come first.
This is done to support overriding of converters.

