package com.agoda.ninjato.policy

import com.agoda.ninjato.http.Request

/**
 * Base policy interface that allows to apply custom strategies and decisions on retries and fallbacks.
 */
interface Policy<T> {
    /**
     * Evaluation of the policy. Based on the type, should return different types of resolution.
     *
     * @param request request instance that has failed executing.
     * @param throwable error that occurred during request execution.
     * @return policy's resolution.
     */
    fun evaluate(request: Request, throwable: Throwable): T
}
