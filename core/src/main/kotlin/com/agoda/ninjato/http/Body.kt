package com.agoda.ninjato.http

import com.agoda.ninjato.converter.BodyConverter
import com.agoda.ninjato.converter.ConverterFactories
import com.agoda.ninjato.exception.MissingConverterException
import com.agoda.ninjato.reflect.TypeReference.Companion.reifiedType
import java.io.InputStream
import kotlin.reflect.KProperty

/**
 * Body entity of every [request][Request] and [response][Response].
 * Stores the actual body value as [byte array][ByteArray] but can transform it to [string][String]
 * and [stream][InputStream]
 *
 * @param body actual body value
 * @param mediaType type of the body value and it's [charset][java.nio.charset.Charset]
 */
class Body(
        private val body: ByteArray,
        val mediaType: MediaType = MediaType.Plain()
) {
       constructor(body: String, mediaType: MediaType = MediaType.Plain()) : this(body.toByteArray(mediaType.charset), mediaType)

    /**
     * Amount of bytes in the body's value
     */
    val contentLength: Int
        get() = body.size

    /**
     * Represents the body's value as [byte array][ByteArray]
     */
    val asByteArray: ByteArray
        get() = body

    /**
     * Represents the body's value as [string][String] with media type's [charset][java.nio.charset.Charset]
     */
    val asString: String
        get() = body.toString(mediaType.charset)

    /**
     * Represents the body's value as [input stream][InputStream]
     */
    val asStream: InputStream
        get() = body.inputStream()

    @Suppress("UNCHECKED_CAST")
    internal class Delegate(val factories: ConverterFactories) {
        var body: Body? = null

        operator fun getValue(thisRef: Any, property: KProperty<*>) = body

        inline operator fun <reified T> setValue(thisRef: Any, property: KProperty<*>, value: T) {
            body = when (value) {
                is Body -> value
                is String -> Body(value)
                is ByteArray -> Body(value)
                else -> {
                    var converted: Body? = null

                    for (factory in factories.resolve()) {
                        val converter = factory.requestConverter(reifiedType<T>()) as? BodyConverter<T, Body>

                        if (converter != null) {
                            converted = converter.convert(value)
                            break
                        }
                    }

                    converted ?: throw MissingConverterException(
                            if (thisRef is Request.Configurator.WithBody) thisRef.fullUrl ?: thisRef.endpointUrl!!
                            else "unknown",
                            T::class.java.simpleName
                    )
                }
            }
        }
    }
}
