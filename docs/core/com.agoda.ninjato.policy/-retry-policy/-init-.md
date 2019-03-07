[core](../../index.md) / [com.agoda.ninjato.policy](../index.md) / [RetryPolicy](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`RetryPolicy()`

Retry policy.
This policy gets invoked when request has failed to complete successfully.

The lowest policy present in the DSL cascade ([HttpClient](../../com.agoda.ninjato.http/-http-client/index.md)
-&gt; [Api](../../com.agoda.ninjato/-api/index.md) -&gt; [Request.Configurator](../../com.agoda.ninjato.http/-request/-configurator/index.md)) is selected for invocation.

Retry policies gives the ability to supply custom logic and decide whether you need to retry executing
your requests or not.

**See Also**

[Retry](../-retry/index.md)

