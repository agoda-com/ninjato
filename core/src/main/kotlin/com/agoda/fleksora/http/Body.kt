package com.agoda.fleksora.http

open class Body(
        private val body: ByteArray,
        val mediaType: MediaType = MediaType.Plain
) {

    constructor(body: String, mediaType: MediaType = MediaType.Plain) : this(body.toByteArray(mediaType.charset ?: Charsets.UTF_8), mediaType)

    val contentLength: Int
        get() = body.size

    val asByteArray: ByteArray
        get() = body

    val asString: String
        get() = body.toString(mediaType.charset ?: Charsets.UTF_8)
}
