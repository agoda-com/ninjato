package com.agoda.fleksora.http

import java.nio.charset.Charset

open class MediaType(val id: String, val charset: Charset? = null) {

    object Plain : MediaType("text/plain")
    object Html : MediaType("text/html")
    object JavaScript : MediaType("text/javascript")
    object Css : MediaType("text/css")
    object Json : MediaType("application/json")

}
