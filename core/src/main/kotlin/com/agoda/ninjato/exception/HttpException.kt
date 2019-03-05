package com.agoda.ninjato.exception

class HttpException(url: String, code: Int) : Throwable("HttpClient returned $code code for the request with url $url")
