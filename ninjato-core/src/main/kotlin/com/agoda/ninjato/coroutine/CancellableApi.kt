package com.agoda.ninjato.coroutine

import com.agoda.ninjato.Api
import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.http.Method
import com.agoda.ninjato.http.Request

abstract class CancellableApi(
        client: HttpClient,
        config: Api.() -> Unit = {}
): Api(client, config) {

    /**
     * Executes the GET request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> Get(configurator: Request.Configurator.() -> Unit): T = prepare(
        Method.Get, configurator)

    /**
     * Executes the HEAD request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> Head(configurator: Request.Configurator.() -> Unit): T = prepare(Method.Head, configurator)

    /**
     * Executes the POST request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> Post(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Post, configurator)

    /**
     * Executes the PUT request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> Put(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Put, configurator)

    /**
     * Executes the DELETE request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> Delete(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Delete, configurator)

    /**
     * Executes the OPTIONS request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> Options(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Options, configurator)

    /**
     * Executes the PATCH request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> Patch(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Patch, configurator)
}