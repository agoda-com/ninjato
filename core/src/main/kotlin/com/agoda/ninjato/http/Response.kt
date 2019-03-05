package com.agoda.ninjato.http

open class Response {
    lateinit var request: Request

    var code: Int = 200
    val headers: MutableMap<String, MutableList<String>> = mutableMapOf()
    var body: Body? = null

    val isSuccess: Boolean
        get() = code == 200

    abstract class Factory {
        abstract fun create(): Response
    }
}
