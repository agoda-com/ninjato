[core](../index.md) / [com.agoda.ninjato.policy](./index.md)

## Package com.agoda.ninjato.policy

### Types

| Name | Summary |
|---|---|
| [FallbackPolicy](-fallback-policy/index.md) | `abstract class FallbackPolicy : `[`Policy`](-policy/index.md)`<`[`Request`](../com.agoda.ninjato.http/-request/index.md)`>`<br>Fallback policy. This policy gets invoked only if [retry policy](-retry-policy/index.md)'s resolution was not [DoNotRetry](-retry/-do-not-retry.md). |
| [Policy](-policy/index.md) | `interface Policy<T>`<br>Base policy interface that allows to apply custom strategies and decisions on retries and fallbacks. |
| [Retry](-retry/index.md) | `sealed class Retry`<br>Resolutions of the [retry policies](-retry-policy/index.md) |
| [RetryPolicy](-retry-policy/index.md) | `abstract class RetryPolicy : `[`Policy`](-policy/index.md)`<`[`Retry`](-retry/index.md)`>`<br>Retry policy. This policy gets invoked when request has failed to complete successfully. |
