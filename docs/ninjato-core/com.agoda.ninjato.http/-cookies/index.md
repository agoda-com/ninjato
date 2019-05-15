[ninjato-core](../../index.md) / [com.agoda.ninjato.http](../index.md) / [Cookies](./index.md)

# Cookies

`class Cookies`

DSL context for adding cookies to the headers of [request](../-request/index.md).
Supports addition of a single cookie with it's parameters.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Cookies()`<br>DSL context for adding cookies to the headers of [request](../-request/index.md). Supports addition of a single cookie with it's parameters. |

### Properties

| Name | Summary |
|---|---|
| [domain](domain.md) | `var domain: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Domain limiter of the cookie. |
| [expires](expires.md) | `var expires: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>Expiration date of the cookie in seconds since epoch (GMT timezone). |
| [isHttpOnly](is-http-only.md) | `var isHttpOnly: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>`HttpOnly` marker of the cookie. |
| [isSecure](is-secure.md) | `var isSecure: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>`Secure` marker of the cookie. |
| [maxAge](max-age.md) | `var maxAge: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>Maximum age of the cookie in seconds. |
| [path](path.md) | `var path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Endpoint path limiter of the cookie. |

### Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | `operator fun invoke(receiver: `[`Cookies`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [to](to.md) | `infix fun `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.to(value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the name and value of the cookie. |
