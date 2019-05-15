package com.agoda.ninjato.exception

import com.agoda.ninjato.http.Request
import com.agoda.ninjato.http.Response

/**
 * This exception is thrown when [request][Request] returns error code and the
 * inferred (request) return type is not [response][Response].
 *
 * Library throws this exception because in case you are requesting not a raw body, any error returned
 * by server means that response contains [body][com.agoda.ninjato.http.Body] that is already incompatible
 * with the expected type or not awaited by the user.
 *
 * @param request request that has been executed
 * @param response response that server has returned
 */
class HttpException(val request: Request, val response: Response) : Throwable("HttpClient returned " +
        "${response.code} code for the request with url ${request.url}")
