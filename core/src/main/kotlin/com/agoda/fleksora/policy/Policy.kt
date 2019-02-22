package com.agoda.fleksora.policy

import com.agoda.fleksora.http.Request

interface Policy<T> {
    fun evaluate(request: Request, throwable: Throwable): T
}
