package com.agoda.ninjato.misc

import com.agoda.ninjato.http.Body
import com.agoda.ninjato.http.MediaType
import com.agoda.ninjato.http.Parameters

fun formUrlEncoded(tail: Parameters.() -> Unit)
        = Body(Parameters().apply(tail).resolve().toUrlEncoded(), MediaType.FormUrlEncoded())
