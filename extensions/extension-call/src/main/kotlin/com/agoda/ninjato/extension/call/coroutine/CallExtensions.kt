package com.agoda.ninjato.extension.call.coroutine

import com.agoda.ninjato.coroutine.Api
import com.agoda.ninjato.extension.call.Call


/**
 * Wraps the network call with [Call]. If no exceptions were thrown, the inferred return type will be
 * passed as the [result][Call.Success.result] property of [Success][Call.Success] wrapper.
 *
 * If any exception has been raised, you will get [Call.Failure] as result with [throwable][Call.Failure.throwable] property.
 *
 * @param receiver tail lambda with the actual API call invocation.
 */
suspend inline fun <reified T> Api.call(crossinline receiver: suspend Api.() -> T): Call<T> = try {
    Call.Success(receiver(this))
} catch (throwable: Throwable) {
    Call.Failure(throwable)
}
