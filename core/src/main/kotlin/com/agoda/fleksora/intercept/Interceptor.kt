package com.agoda.fleksora.intercept

interface Interceptor<T> {
    var id: String
    fun intercept(instance: T): T
}
