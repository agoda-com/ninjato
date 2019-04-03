package com.agoda.ninjato.extension.call

/**
 * Wrapper class for API calls. Implemented as sealed class with [Success] and [Failure] subclasses.
 *
 * NOTE: this wrapper is not asynchronous.
 *
 * NOTE: this wrapper will be returned as [Success] if [Response][com.agoda.ninjato.http.Response] entity
 * was requested even if remote server returned non 200 response code.
 */
sealed class Call<T> {
    class Success<T>(val result: T) : Call<T>()
    class Failure<T>(val throwable: Throwable) : Call<T>()
}
