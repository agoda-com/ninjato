package com.agoda.ninjato.intercept

interface Interceptor<T> {
    var id: String
    fun intercept(instance: T): T
}
