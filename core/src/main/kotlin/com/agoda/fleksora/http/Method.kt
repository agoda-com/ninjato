package com.agoda.fleksora.http

sealed class Method(val name: String, val requiresBody: Boolean) {
    object Get : Method("get", false)
    object Head : Method("head", false)
    object Post : Method("post", true)
    object Put : Method("put", true)
    object Delete : Method("delete", false)
    object Options : Method("options", false)
    object Patch : Method("patch", true)
}
