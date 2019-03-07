[core](../../index.md) / [com.agoda.ninjato.policy.impl](../index.md) / [DefaultRetryPolicy](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`DefaultRetryPolicy(retries: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`)`

Default implementation of [retry policy](../../com.agoda.ninjato.policy/-retry-policy/index.md) bundled with the library.
Keep note that this policy is `NOT` used by default and must be manually provided if needed.

### Parameters

`retries` - amount of retries that should occur for every failed request regardless of the reason.

`delay` - amount of milliseconds that will be passed to `Thread.sleep()` to perform the delay.