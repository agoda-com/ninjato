package com.agoda.ninjato.http

import com.agoda.ninjato.misc.toUTCDateTime

/**
 * DSL context for adding cookies to the headers of [request][com.agoda.ninjato.http.Request].
 * Supports addition of a single cookie with it's parameters.
 */
class Cookies {
    private var cookie: Pair<String, String>? = null

    /**
     * Expiration date of the cookie in seconds since epoch (UTC timezone).
     */
    var expires: Long = 0L

    /**
     * Maximum age of the cookie in seconds.
     */
    var maxAge: Long = 0L

    /**
     * Endpoint path limiter of the cookie.
     */
    var path: String = ""

    /**
     * Domain limiter of the cookie.
     */
    var domain: String = ""

    /**
     * `Secure` marker of the cookie.
     */
    var isSecure = false

    /**
     * `HttpOnly` marker of the cookie.
     */
    var isHttpOnly = false

    /**
     * Sets the name and value of the cookie.
     *
     * IMPORTANT: this invocation is mandatory and should only happen once.
     *
     * @param value value of a cookie.
     * @throws IllegalStateException if called more that once in the same context.
     */
    infix fun String.to(value: String) {
        cookie?.let { throw IllegalStateException("Cookie's name and value has been already set - ${it.first}=${it.second}") }
        cookie = Pair(this, value)
    }

    operator fun invoke(receiver: Cookies.() -> Unit) {
        this.apply(receiver)
    }

    internal fun resolve() = cookie.takeIf {
        it != null
    }?.let {
        StringBuilder("${it.first}=${it.second}").apply {
            if (expires > 0L) append("; expires=${expires.toUTCDateTime()}")
            if (maxAge > 0L) append("; max-age=$maxAge")
            if (path.isNotBlank()) append("; path=$path")
            if (domain.isNotBlank()) append("; domain=$domain")
            if (isSecure) append("; Secure")
            if (isHttpOnly) append("; HttpOnly")
        }.toString()
    } ?: throw IllegalStateException("Cannot set cookie without name and value!")
}
