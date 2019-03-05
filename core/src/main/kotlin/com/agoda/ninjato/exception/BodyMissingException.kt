package com.agoda.ninjato.exception

class BodyMissingException(url: String, method: String) : Throwable("Request with url $url and $method method require body entity, but none provided!")
