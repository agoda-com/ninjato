package com.agoda.ninjato.http

sealed class Method(val name: String, val requiresBody: Boolean) {
    object Get : Method("GET", false)
    object Head : Method("HEAD", false)
    object Post : Method("POST", true)
    object Put : Method("PUT", true)
    object Delete : Method("DELETE", false)
    object Options : Method("OPTIONS", false)
    object Patch : Method("PATCH", true)
}
