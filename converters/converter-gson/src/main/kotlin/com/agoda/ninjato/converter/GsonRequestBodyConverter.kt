package com.agoda.ninjato.converter

import com.agoda.ninjato.http.Body
import com.agoda.ninjato.http.MediaType
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter

class GsonRequestBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : BodyConverter<T, Body> {
    override fun convert(instance: T): Body {
        val stream = ByteArrayOutputStream()
        val writer = gson.newJsonWriter(OutputStreamWriter(stream, Charsets.UTF_8))
        adapter.write(writer, instance)
        writer.close()
        return Body(stream.toByteArray(), MediaType.Json())
    }
}
