package com.agoda.fleksora.client

import com.agoda.fleksora.http.Body
import com.agoda.fleksora.http.HttpClient
import com.agoda.fleksora.http.Request
import com.agoda.fleksora.http.Response
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody

class FleksoraOkHttpClient(private val client: OkHttpClient) : HttpClient() {

    override fun execute(request: Request): Response {
        val builder = okhttp3.Request.Builder()
                .url(request.url)
                .method(request.method.name, request.body?.let { RequestBody.create(MediaType.parse(it.mediaType.toString()), it.asByteArray) })

        request.headers.forEach { key, values ->
            values.forEach { builder.addHeader(key, it) }
        }

        val okRequest = builder.build()

        val start = System.currentTimeMillis()
        val okResponse = client.newCall(okRequest).execute()
        val end = System.currentTimeMillis()

        return (responseFactory?.create() ?: Response()).also {
            it.request = request
            it.time = end - start
            it.code = okResponse.code()
            it.headers.putAll(okResponse.headers().toMultimap())
            it.body = okResponse.body()?.let { body ->
                val bytes = body.bytes()
                val contentType = body.contentType()

                val id = contentType?.let { type -> "${type.type()}/${type.subtype()}"} ?: ""
                val charset = contentType?.charset()

                val mediaType = com.agoda.fleksora.http.MediaType(id, charset ?: Charsets.UTF_8)

                Body(bytes, mediaType)
            }
        }
    }

}
