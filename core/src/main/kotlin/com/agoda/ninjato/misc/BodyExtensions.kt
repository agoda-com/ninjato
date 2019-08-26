package com.agoda.ninjato.misc

import com.agoda.ninjato.http.Body
import com.agoda.ninjato.http.MediaType
import com.agoda.ninjato.http.Parameters
import java.io.File

fun formUrlEncoded(tail: Parameters.() -> Unit)
        = Body(Parameters().apply(tail).resolve().toUrlEncoded(), MediaType.FormUrlEncoded())

fun file(file: File, mediaType: MediaType) = Body(file.readBytes(), mediaType)
