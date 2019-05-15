package com.agoda.ninjato.misc

import com.agoda.ninjato.converter.BodyConverter
import com.agoda.ninjato.exception.MissingConverterException
import com.agoda.ninjato.http.Body
import com.agoda.ninjato.http.MediaType
import com.agoda.ninjato.http.Parameters
import com.agoda.ninjato.http.Request.Configurator.WithBody
import com.agoda.ninjato.reflect.TypeReference.Companion.reifiedType

inline fun WithBody.formUrlEncoded(tail: Parameters.() -> Unit)
        = Body(Parameters().apply(tail).resolve().toUrlEncoded(), MediaType.FormUrlEncoded())

inline fun <reified T> WithBody.convert(body: T): Body {
    var converted: Body? = null

    for (factory in converterFactories.resolve()) {
        val converter = factory.requestConverter(reifiedType<T>()) as? BodyConverter<T, Body>

        if (converter != null) {
            converted = converter.convert(body)
            break
        }
    }

    return converted ?: throw MissingConverterException(
            fullUrl ?: endpointUrl!!,
            T::class.java.simpleName ?: ""
    )
}
