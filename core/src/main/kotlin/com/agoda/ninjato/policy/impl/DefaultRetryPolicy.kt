package com.agoda.ninjato.policy.impl

import com.agoda.ninjato.http.Request
import com.agoda.ninjato.policy.Retry.DoNotRetry
import com.agoda.ninjato.policy.Retry.WithDelay
import com.agoda.ninjato.policy.RetryPolicy

class DefaultRetryPolicy(
        private val retryCount: Int,
        private val retryDelay: Long
) : RetryPolicy() {
    override fun evaluate(request: Request, throwable: Throwable) = if (request.retries < retryCount) {
        WithDelay { Thread.sleep(retryDelay) }
    } else {
        DoNotRetry
    }
}
