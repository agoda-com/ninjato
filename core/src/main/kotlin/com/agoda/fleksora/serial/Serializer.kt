package com.agoda.fleksora.serial

import com.agoda.fleksora.http.Body
import com.agoda.fleksora.http.Request
import com.agoda.fleksora.reflect.TypeReference.Companion.reifiedType
import java.lang.reflect.Type
import kotlin.reflect.KProperty

interface Serializer<in I, out O> {
    fun serialize(instance: I): O

    interface Factory {
        fun requestSerializer(type: Type): Serializer<*, Body>?
        fun responseSerializer(type: Type): Serializer<Body, *>?

        class Delegate(val factories: List<Serializer.Factory>) {
            var body: Body? = null

            operator fun getValue(thisRef: Request.Builder.WithBody, property: KProperty<*>) = body

            inline operator fun <reified T> setValue(thisRef: Request.Builder.WithBody, property: KProperty<*>, value: T) {
                var serializer: Serializer<T, Body>? = null

                factories.firstOrNull {
                    serializer = it.requestSerializer(reifiedType<T>()) as? Serializer<T, Body>
                    serializer != null
                }?.let {
                    body = serializer!!.serialize(value)
                } ?: run {
                    body = when (value) {
                        is String -> Body(value)
                        is ByteArray -> Body(value.toString())
                        else -> throw UnsupportedOperationException("Couldn't serialize provided body. Did you registered " +
                                "Serializer.Factory that provides serializer for ${T::class.java.simpleName} type?")
                    }
                }
            }
        }
    }
}
