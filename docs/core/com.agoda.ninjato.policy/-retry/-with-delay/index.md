[core](../../../index.md) / [com.agoda.ninjato.policy](../../index.md) / [Retry](../index.md) / [WithDelay](./index.md)

# WithDelay

`class WithDelay : `[`Retry`](../index.md)

This resolution indicates that library should attempt retry after a delay.
Occurred exception will be suppressed.

### Parameters

`delay` - lambda that should delay the call. By default it invokes `Thread.sleep(1000)`.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `WithDelay(delay: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = { Thread.sleep(1000) })`<br>This resolution indicates that library should attempt retry after a delay. Occurred exception will be suppressed. |

### Properties

| Name | Summary |
|---|---|
| [delay](delay.md) | `val delay: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>lambda that should delay the call. By default it invokes `Thread.sleep(1000)`. |
