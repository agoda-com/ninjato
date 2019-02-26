package com.agoda.fleksora.serial

import com.agoda.fleksora.http.Body
import java.lang.reflect.Type

interface Serializer<in I, out O> {
    fun serializer(instance: I): O

    interface Factory {
        fun requestSerializer(type: Type): Serializer<*, Body>
        fun responseSerializer(type: Type): Serializer<Body, *>
    }
}
