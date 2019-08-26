[ninjato-core](../../index.md) / [com.agoda.ninjato.policy](../index.md) / [FallbackPolicy](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`FallbackPolicy()`

Fallback policy.
This policy gets invoked only if [retry policy](../-retry-policy/index.md)'s resolution was not [DoNotRetry](../-retry/-do-not-retry.md).

The lowest policy present in the DSL cascade ([HttpClient](../../com.agoda.ninjato.http/-http-client/index.md)
-&gt; [Api](../../com.agoda.ninjato/-api/index.md) -&gt; [Request.Configurator](../../com.agoda.ninjato.http/-request/-configurator/index.md)) is selected for invocation.

Fallback policies gives the ability to modify your [request](../../com.agoda.ninjato.http/-request/index.md) or provide completely different [request](../../com.agoda.ninjato.http/-request/index.md)
before attempting a retry.

