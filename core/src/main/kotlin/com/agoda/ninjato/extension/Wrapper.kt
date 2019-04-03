package com.agoda.ninjato.extension

import com.agoda.ninjato.Api
import com.agoda.ninjato.http.Request

/**
 * This class allows contributors to write wrapper-like extension functions.
 *
 * In current release of Kotlin, the compiler cannot auto-cast the inferred type inside inlined lambdas.
 *
 * It means that you still have to manually specify the reified type if you're calling such a function inside
 * a lambda that is being passed to another reified inline function and their types are the same.
 *
 * Here's the example of usage:
 *
 * ```
 * inline fun Api.observable(crossinline receiver: Wrapper<T>.() -> T): Observable<T> = Observable.fromCallable {
 *     receiver(object : Wrapper<T>(this) {})
 * }
 *
 * class YourApi : Api {
 *     fun search(query: String): Observable<SearchResult> = observable {
 *         get {
 *             ...
 *         }
 *     }
 * }
 * ```
 *
 * Without such a wrapper class, you would have to manually specify your type that actually can be inferred:
 *
 * ```
 * class YourApi : Api {
 *     fun search(query: String): Observable<SearchResult> = observable {
 *         get<SearchResult> {
 *             ...
 *         }
 *     }
 * }
 * ```
 *
 * @param api instance of the [Api] to wrap around.
 */
abstract class Wrapper<T>(@PublishedApi internal val api: Api) {
    /**
     * Executes the GET request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> get(configurator: Request.Configurator.() -> Unit): T = api.get(configurator)

    /**
     * Executes the HEAD request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> head(configurator: Request.Configurator.() -> Unit): T = api.head(configurator)

    /**
     * Executes the POST request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> post(configurator: Request.Configurator.WithBody.() -> Unit): T = api.post(configurator)

    /**
     * Executes the PUT request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> put(configurator: Request.Configurator.WithBody.() -> Unit): T = api.put(configurator)

    /**
     * Executes the DELETE request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> delete(configurator: Request.Configurator.WithBody.() -> Unit): T = api.delete(configurator)

    /**
     * Executes the OPTIONS request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> options(configurator: Request.Configurator.WithBody.() -> Unit): T = api.options(configurator)

    /**
     * Executes the PATCH request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> patch(configurator: Request.Configurator.WithBody.() -> Unit): T = api.patch(configurator)
}
