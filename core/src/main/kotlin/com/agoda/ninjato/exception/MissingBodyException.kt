package com.agoda.ninjato.exception

import com.agoda.ninjato.http.Request

/**
 * This exception is thrown when [method][com.agoda.ninjato.http.Method] of [request][Request]
 * requires body to be present but cannot be found by the library.
 */
class MissingBodyException(val request: Request) : Throwable("Request with url ${request.url} and " +
        "${request.method.name} method require body entity, but none provided!")
