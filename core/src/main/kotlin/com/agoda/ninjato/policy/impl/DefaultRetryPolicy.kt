package com.agoda.ninjato.policy.impl

import com.agoda.ninjato.http.Request
import com.agoda.ninjato.policy.Retry.DoNotRetry
import com.agoda.ninjato.policy.Retry.WithDelay
import com.agoda.ninjato.policy.RetryPolicy

/**
 * Default implementation of [retry policy][RetryPolicy] bundled with the library.
 * Keep note that this policy is `NOT` used by default and must be manually provided if needed.
 *
 * @param retries amount of retries that should occur for every failed request regardless of the reason.
 * @param delay amount of milliseconds that will be passed to `Thread.sleep()` to perform the delay.
 */
class DefaultRetryPolicy(
        private val retries: Int,
        private val delay: Long
) : RetryPolicy() {
    override fun evaluate(request: Request, throwable: Throwable) = if (request.retries < retries) {
        WithDelay { Thread.sleep(delay) }
    } else {
        DoNotRetry
    }
}
