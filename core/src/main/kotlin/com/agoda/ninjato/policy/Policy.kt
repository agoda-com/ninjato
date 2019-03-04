package com.agoda.ninjato.policy

import com.agoda.ninjato.http.Request

interface Policy<T> {
    fun evaluate(request: Request, throwable: Throwable): T
}
