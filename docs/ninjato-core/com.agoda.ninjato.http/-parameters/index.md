[ninjato-core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [Parameters](./index.md)

# Parameters

`class Parameters`

DSL context for aggregating query parameters of [request](../-request/index.md).
Supports additions of single value of parameter mapped to a key.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Parameters()`<br>DSL context for aggregating query parameters of [request](../-request/index.md). Supports additions of single value of parameter mapped to a key. |

### Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | `operator fun invoke(receiver: `[`Parameters`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [plusAssign](plus-assign.md) | `operator fun plusAssign(parameter: `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds provided parameter to the aggregation. |
| [to](to.md) | `infix fun `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.to(value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds provided parameter to the aggregation. |
