package com.agoda.ninjato.client

import com.agoda.ninjato.http.Body
import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.http.Request
import com.agoda.ninjato.http.Response
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class NinjatoOkHttpClient(
        private val client: OkHttpClient,
        requestFactory: Request.Factory? = null,
        responseFactory: Response.Factory? = null,
        config: HttpClient.() -> Unit = {}
) : HttpClient(requestFactory, responseFactory, config) {
    override fun execute(request: Request): Response {
        return client.newCall(buildRequest(request)).execute().use { 
            parseResponse(request, it)
        }
    }

    override suspend fun executeAsync(request: Request): Response {
        return suspendCancellableCoroutine { continuation ->
            val call = client.newCall(buildRequest(request)).also { 
                it.enqueue(callback(request, continuation))
            }
            
            continuation.invokeOnCancellation { 
                call.cancel()
            }
        }
    }
    
    private fun buildRequest(request: Request): okhttp3.Request {
        val builder = okhttp3.Request.Builder()
                .url(request.url)
                .method(request.method.name, request.body?.let { RequestBody.create(MediaType.parse(it.mediaType.toString()), it.asByteArray) })

        for((key, value) in request.headers) {
            for (it in value) builder.addHeader(key, it)
        }
        
        return builder.build()
    }
    
    private fun parseResponse(request: Request, response: okhttp3.Response): Response {
        val entity = responseFactory?.create() ?: Response()

        entity.request = request
        entity.code = response.code()
        entity.headers.putAll(response.headers().toMultimap())
        
        val body = response.body()
        
        if (body != null) {
            val bytes = body.bytes()
            val contentType = body.contentType()

            val id = contentType?.let { type -> "${type.type()}/${type.subtype()}" } ?: ""
            val charset = contentType?.charset()

            val mediaType = com.agoda.ninjato.http.MediaType(id, charset ?: Charsets.UTF_8)

            entity.body = Body(bytes, mediaType)
        }
        
        return entity
    }

    private fun callback(request: Request, continuation: CancellableContinuation<Response>) = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            continuation.resumeWithException(e)
        }

        override fun onResponse(call: Call, response: okhttp3.Response) {
            response.use { continuation.resume(parseResponse(request, it)) }
        }
    }
}
