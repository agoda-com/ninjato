package com.agoda.ninjato.exception

/**
 * This exception is thrown when library cannot acquire [converter][com.agoda.ninjato.converter.BodyConverter]
 * from the provided [factories][com.agoda.ninjato.converter.BodyConverter.Factory] and thus is unable
 * to convert your provided value to [body][com.agoda.ninjato.http.Body].
 */
class MissingConverterException(val url: String, val type: String) : Throwable("Couldn't convert body for request with url $url." +
        " Did you registered BodyConverter.Factory that provides serializer for $type type?")
