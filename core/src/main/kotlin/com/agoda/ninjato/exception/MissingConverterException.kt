package com.agoda.ninjato.exception

class MissingConverterException(url: String, type: String) : Throwable("Couldn't convert body for request with url $url." +
        " Did you registered BodyConverter.Factory that provides serializer for $type type?")
