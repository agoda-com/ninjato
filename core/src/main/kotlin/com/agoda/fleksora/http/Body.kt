package com.agoda.fleksora.http

open class Body(
        val body: ByteArray,
        val mediaType: MediaType
) {

    constructor(body: String) : this(body.toByteArray(), MediaType.Plain)
    constructor(body: String, mediaType: MediaType) : this(body.toByteArray(), mediaType)

    val contentLength: Int
        get() = body.size

    companion object {
        protected const val CHARSET_UTF8 = "charset=utf-8"
    }
}
