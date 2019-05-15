package com.agoda.ninjato.reflect

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

@PublishedApi
internal abstract class TypeReference<T> protected constructor(): TypeToken {
    override val type: Type by lazy {
        javaClass.genericSuperclass.let { superClass ->
            if (superClass is Class<*>) {
                throw IllegalArgumentException("TypeReference constructed without actual type information")
            }

            (superClass as ParameterizedType).actualTypeArguments.single()
        }
    }

    companion object {
        inline fun <reified T> reifiedType(): Type = object : TypeReference<T>(){}.type
    }
}
