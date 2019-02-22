package com.agoda.fleksora.http

open class MediaType(val id: String) {

    object Plain : MediaType("text/plain")
    object Html : MediaType("text/html")
    object JavaScript : MediaType("text/javascript")
    object Css : MediaType("text/css")
    object Json : MediaType("application/json")

}
