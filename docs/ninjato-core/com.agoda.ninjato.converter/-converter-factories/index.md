[ninjato-core](../../index.md) / [com.agoda.ninjato.converter](../index.md) / [ConverterFactories](./index.md)

# ConverterFactories

`class ConverterFactories`

DSL context for aggregating [converter factories](../-body-converter/-factory/index.md).
Supports additions of single factory and iterables of factories.

When resolving all the aggregations across the DSL cascade, keep in mind,
that factories that were defined on the lowest level will come first.
This is done to support overriding of converters.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ConverterFactories()`<br>DSL context for aggregating [converter factories](../-body-converter/-factory/index.md). Supports additions of single factory and iterables of factories. |

### Functions

| Name | Summary |
|---|---|
| [plusAssign](plus-assign.md) | `operator fun plusAssign(factory: `[`Factory`](../-body-converter/-factory/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds provided factory to the aggregation.`operator fun plusAssign(factories: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`Factory`](../-body-converter/-factory/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds provided factories to the aggregation. |
