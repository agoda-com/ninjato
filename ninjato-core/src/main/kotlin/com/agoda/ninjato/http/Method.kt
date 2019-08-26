package com.agoda.ninjato.http

/**
 * Method of the http [request][Request].
 *
 * @param name name of the method
 * @param requiresBody if set to `true`, library will check that the [body][Body] for [requests][Request]
 * is provided and will throw [exception][com.agoda.ninjato.exception.MissingBodyException] if it is not found.
 */
sealed class Method(val name: String, val requiresBody: Boolean) {
    /**
     * Represents the `GET` http method. [Body] is not required.
     */
    object Get : Method("GET", false)

    /**
     * Represents the `HEAD` http method. [Body] is not required.
     */
    object Head : Method("HEAD", false)

    /**
     * Represents the `POST` http method. [Body] is required.
     */
    object Post : Method("POST", true)

    /**
     * Represents the `PUT` http method. [Body] is required.
     */
    object Put : Method("PUT", true)

    /**
     * Represents the `DELETE` http method. [Body] is not required.
     */
    object Delete : Method("DELETE", false)

    /**
     * Represents the `OPTIONS` http method. [Body] is not required.
     */
    object Options : Method("OPTIONS", false)

    /**
     * Represents the `PATCH` http method. [Body] is required.
     */
    object Patch : Method("PATCH", true)
}
