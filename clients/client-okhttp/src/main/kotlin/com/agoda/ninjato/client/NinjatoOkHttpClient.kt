package com.agoda.ninjato.client

import com.agoda.ninjato.http.Body
import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.http.Request
import com.agoda.ninjato.http.Response
import com.agoda.ninjato.log.Level
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody

class NinjatoOkHttpClient(private val client: OkHttpClient) : HttpClient() {
    override fun execute(request: Request): Response {
        val builder = okhttp3.Request.Builder()
                .url(request.url)
                .method(request.method.name, request.body?.let { RequestBody.create(MediaType.parse(it.mediaType.toString()), it.asByteArray) })

        for((key, value) in request.headers) {
            value.forEach {
                builder.addHeader(key, it)
            }
        }

        val okRequest = builder.build()

        logger?.log(Level.Debug, "Converting request to okhttp3.Request -> $okRequest")

        val start = System.currentTimeMillis()
        val okResponse = client.newCall(okRequest).execute()
        val end = System.currentTimeMillis()

        return (responseFactory?.create() ?: Response()).also {
            logger?.log(Level.Debug, "Converting okhttp3.Response to respones -> $okResponse")

            it.request = request
            it.time = end - start
            it.code = okResponse.code()
            it.headers.putAll(okResponse.headers().toMultimap())
            it.body = okResponse.body()?.let { body ->
                val bytes = body.bytes()
                val contentType = body.contentType()

                val id = contentType?.let { type -> "${type.type()}/${type.subtype()}"} ?: ""
                val charset = contentType?.charset()

                val mediaType = com.agoda.ninjato.http.MediaType(id, charset ?: Charsets.UTF_8)

                Body(bytes, mediaType)
            }
        }
    }
}
