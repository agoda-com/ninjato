[core](../../../index.md) / [com.agoda.ninjato.policy](../../index.md) / [Retry](../index.md) / [WithDelay](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`WithDelay(delay: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = { Thread.sleep(1000) })`

This resolution indicates that library should attempt retry after a delay.
Occurred exception will be suppressed.

### Parameters

`delay` - lambda that should delay the call. By default it invokes `Thread.sleep(1000)`.