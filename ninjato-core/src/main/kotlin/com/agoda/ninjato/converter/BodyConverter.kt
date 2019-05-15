package com.agoda.ninjato.converter

import com.agoda.ninjato.http.Body
import java.lang.reflect.Type

/**
 * Interface for converting instances of type `I` to type `O`.
 */
interface BodyConverter<in I, out O> {
    fun convert(instance: I): O

    /**
     * Factory interface for providing converters to and from [body][Body].
     */
    interface Factory {
        /**
         * Function that provides converter from custom type to [body][Body].
         *
         * @param type type that needs to be converted from.
         * @return converter from provided type to body, `null` if your factory cannot serve provided type.
         */
        fun requestConverter(type: Type): BodyConverter<*, Body>?

        /**
         * Function that provides converter from [body][Body] to custom type.
         *
         * @param type type that needs to be converted to.
         * @return converter from body to provided type, `null` if your factory cannot serve provided type.
         */
        fun responseConverter(type: Type): BodyConverter<Body, *>?
    }
}
