package com.agoda.ninjato.http

import java.nio.charset.Charset

/**
 * Media type of the [request][Request] or [response][Response] body encoded in specified
 * [charset][Charset].
 *
 * @param id identifier of the content type. `application/json` as an example.
 * @param charset charset of the content. `utf-8` by default.
 */
open class MediaType(val id: String, val charset: Charset = Charsets.UTF_8) {
    /**
     * Represents the `text/plain` content type.
     *
     * @param charset charset of the content. `utf-8` by default.
     */
    class Plain(charset: Charset = Charsets.UTF_8) : MediaType("text/plain", charset)

    /**
     * Represents the `text/html` content type.
     *
     * @param charset charset of the content. `utf-8` by default.
     */
    class Html(charset: Charset = Charsets.UTF_8) : MediaType("text/html", charset)

    /**
     * Represents the `text/javascript` content type.
     *
     * @param charset charset of the content. `utf-8` by default.
     */
    class JavaScript(charset: Charset = Charsets.UTF_8) : MediaType("text/javascript", charset)

    /**
     * Represents the `text/css` content type.
     *
     * @param charset charset of the content. `utf-8` by default.
     */
    class Css(charset: Charset = Charsets.UTF_8) : MediaType("text/css", charset)

    /**
     * Represents the `application/json` content type.
     *
     * @param charset charset of the content. `utf-8` by default.
     */
    class Json(charset: Charset = Charsets.UTF_8) : MediaType("application/json", charset)

    /**
     * Represents the `application/x-www-form-urlencoded` content type.
     *
     * @param charset charset of the content. `utf-8` by default.
     */
    class FormUrlEncoded(charset: Charset = Charsets.UTF_8) : MediaType("application/x-www-form-urlencoded", charset)

    /**
     * Formats the media type and charset to the `Content-Type` http header's value standards.
     *
     * @return http header valid representation of media type.
     */
    override fun toString() = "$id;charset=${charset.name().toLowerCase()}"
}
