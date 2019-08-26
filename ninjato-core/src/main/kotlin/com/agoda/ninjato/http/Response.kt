package com.agoda.ninjato.http

/**
 * Base http entity of the library.
 * [HttpClient] translates it's internal response entities into this one and propagates further.
 */
open class Response {
    lateinit var request: Request

    var code: Int = 200
    val headers: MutableMap<String, MutableList<String>> = mutableMapOf()
    var body: Body? = null

    val isSuccess: Boolean
        get() = code == 200

    /**
     * Factory class for creating instances of request.
     * In case you need to extend [Response] and enrich it with some data of your own,
     * provide instance of factory to [HttpClient].
     */
    abstract class Factory {
        abstract fun create(): Response
    }
}
