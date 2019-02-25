package com.agoda.fleksora.serial

import com.agoda.fleksora.http.Body
import kotlin.reflect.KType

interface Serializer<in I, out O> {
    fun serializer(instance: I): O

    interface Factory {
        fun requestSerializer(type: KType): Serializer<*, Body>
        fun responseSerializer(type: KType): Serializer<Body, *>
    }
}
