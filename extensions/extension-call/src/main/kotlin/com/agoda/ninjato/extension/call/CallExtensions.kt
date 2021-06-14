package com.agoda.ninjato.extension.call

import com.agoda.ninjato.Api

/**
 * Wraps the network call with [Call]. If no exceptions were thrown, the inferred return type will be
 * passed as the [result][Call.Success.result] property of [Success][Call.Success] wrapper.
 *
 * If any exception has been raised, you will get [Call.Failure] as result with [throwable][Call.Failure.throwable] property.
 *
 * @param receiver tail lambda with the actual API call invocation.
 */
inline fun <reified T> Api.call(crossinline receiver: Api.() -> T): Call<T> = try {
    Call.Success(receiver(this))
} catch (throwable: Throwable) {
    Call.Failure(throwable)
}

/**
 * Wraps the network call with [Call] inside coroutine context. If no exceptions were thrown, the inferred return type will be
 * passed as the [result][Call.Success.result] property of [Success][Call.Success] wrapper.
 *
 * If any exception has been raised, you will get [Call.Failure] as result with [throwable][Call.Failure.throwable] property.
 *
 * @param receiver tail lambda with the actual API call invocation.
 */
suspend inline fun <reified T> Api.callAsync(crossinline receiver: suspend Api.() -> T): Call<T> = try {
    Call.Success(receiver(this))
} catch (throwable: Throwable) {
    Call.Failure(throwable)
}
