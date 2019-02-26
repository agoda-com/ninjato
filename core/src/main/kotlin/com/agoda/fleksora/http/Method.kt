package com.agoda.fleksora.http

sealed class Method(val requiresBody: Boolean, val permitsBody: Boolean) {
    object Get : Method(false, false)
    object Head : Method(false, false)
    object Post : Method(true, true)
    object Put : Method(true, true)
    object Delete : Method(false, true)
    object Options : Method(false, true)
    object Patch : Method(true, true)
}
