package com.agoda.fleksora.http

import java.nio.charset.Charset

open class MediaType(val id: String, val charset: Charset = Charsets.UTF_8) {

    class Plain(charset: Charset = Charsets.UTF_8) : MediaType("text/plain", charset)
    class Html(charset: Charset = Charsets.UTF_8) : MediaType("text/html", charset)
    class JavaScript(charset: Charset = Charsets.UTF_8) : MediaType("text/javascript", charset)
    class Css(charset: Charset = Charsets.UTF_8) : MediaType("text/css", charset)
    class Json(charset: Charset = Charsets.UTF_8) : MediaType("application/json", charset)

    override fun toString() = "$id; charset=${charset.name()}"

}
