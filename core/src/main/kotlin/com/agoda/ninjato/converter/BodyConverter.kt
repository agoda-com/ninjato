package com.agoda.ninjato.converter

import com.agoda.ninjato.exception.MissingConverterException
import com.agoda.ninjato.http.Body
import com.agoda.ninjato.http.Request
import com.agoda.ninjato.reflect.TypeReference.Companion.reifiedType
import java.lang.reflect.Type
import kotlin.reflect.KProperty

interface BodyConverter<in I, out O> {
    fun convert(instance: I): O

    interface Factory {
        fun requestConverter(type: Type): BodyConverter<*, Body>?
        fun responseConverter(type: Type): BodyConverter<Body, *>?
    }

    @Suppress("UNCHECKED_CAST")
    class Delegate(val factories: ConverterFactories) {
        var body: Body? = null

        operator fun getValue(thisRef: Request.Configurator.WithBody, property: KProperty<*>) = body

        inline operator fun <reified T> setValue(thisRef: Request.Configurator.WithBody, property: KProperty<*>, value: T) {
            body = when (value) {
                is Body -> value
                is String -> Body(value)
                is ByteArray -> Body(value.toString())
                else -> {
                    var converted: Body? = null

                    for (factory in factories.resolve()) {
                        val converter = factory.requestConverter(reifiedType<T>()) as? BodyConverter<T, Body>

                        if (converter != null) {
                            converted = converter.convert(value)
                            break
                        }
                    }

                    converted ?: throw MissingConverterException(thisRef.fullUrl ?: thisRef.endpointUrl!!, T::class.java.simpleName)
                }
            }
        }
    }
}
