[ninjato-core](../../index.md) / [com.agoda.ninjato.policy](../index.md) / [Retry](./index.md)

# Retry

`sealed class Retry`

Resolutions of the [retry policies](../-retry-policy/index.md)

### Types

| Name | Summary |
|---|---|
| [DoNotRetry](-do-not-retry.md) | `object DoNotRetry : `[`Retry`](./index.md)<br>This resolution indicates that there is no need to attempt retries. Occurred exception will be thrown down the call stack. |
| [WithDelay](-with-delay/index.md) | `class WithDelay : `[`Retry`](./index.md)<br>This resolution indicates that library should attempt retry after a delay. Occurred exception will be suppressed. |
| [WithoutDelay](-without-delay.md) | `object WithoutDelay : `[`Retry`](./index.md)<br>This resolution indicates that library should attempt retry immediately. Occurred exception will be suppressed. |

### Inheritors

| Name | Summary |
|---|---|
| [DoNotRetry](-do-not-retry.md) | `object DoNotRetry : `[`Retry`](./index.md)<br>This resolution indicates that there is no need to attempt retries. Occurred exception will be thrown down the call stack. |
| [WithDelay](-with-delay/index.md) | `class WithDelay : `[`Retry`](./index.md)<br>This resolution indicates that library should attempt retry after a delay. Occurred exception will be suppressed. |
| [WithoutDelay](-without-delay.md) | `object WithoutDelay : `[`Retry`](./index.md)<br>This resolution indicates that library should attempt retry immediately. Occurred exception will be suppressed. |
