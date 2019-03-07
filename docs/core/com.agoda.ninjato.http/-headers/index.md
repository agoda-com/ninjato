[core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [Headers](./index.md)

# Headers

`class Headers`

DSL context for aggregating headers of [request](../-request/index.md).
Supports additions of single and iterable values of headers mapped to a key.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Headers()`<br>DSL context for aggregating headers of [request](../-request/index.md). Supports additions of single and iterable values of headers mapped to a key. |

### Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | `operator fun invoke(receiver: `[`Headers`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [plusAssign](plus-assign.md) | `operator fun plusAssign(header: `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds provided header to the aggregation. |
| [to](to.md) | `infix fun `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.to(value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds provided header to the aggregation.`infix fun `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.to(value: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds provided headers to the aggregation. |
