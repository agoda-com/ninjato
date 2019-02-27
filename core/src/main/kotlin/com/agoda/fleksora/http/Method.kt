package com.agoda.fleksora.http

sealed class Method(val requiresBody: Boolean) {
    object Get : Method(false)
    object Head : Method(false)
    object Post : Method(true)
    object Put : Method(true)
    object Delete : Method(false)
    object Options : Method(false)
    object Patch : Method(true)
}
