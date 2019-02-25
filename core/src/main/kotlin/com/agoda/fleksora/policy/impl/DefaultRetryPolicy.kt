package com.agoda.fleksora.policy.impl

import com.agoda.fleksora.http.Request
import com.agoda.fleksora.policy.Retry.DoNotRetry
import com.agoda.fleksora.policy.Retry.WithDelay
import com.agoda.fleksora.policy.RetryPolicy

class DefaultRetryPolicy(
        private val retryCount: Int,
        private val retryDelay: Long
) : RetryPolicy() {

    override fun evaluate(request: Request, throwable: Throwable) = if (request.retries < retryCount) {
        WithDelay(retryDelay)
    } else {
        DoNotRetry
    }

}
