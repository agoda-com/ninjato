package com.agoda.fleksora.converter

import com.agoda.fleksora.http.Body
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import java.io.InputStreamReader
import java.lang.reflect.Type

class GsonResponseBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : BodyConverter<Body, T> {
    override fun convert(instance: Body): T {
        val stream = instance.asStream
        val reader = gson.newJsonReader(InputStreamReader(stream, instance.mediaType.charset))
        return stream.use { adapter.read(reader) }
    }
}
