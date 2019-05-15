package com.agoda.ninjato.policy

import com.agoda.ninjato.http.Request

/**
 * Fallback policy.
 * This policy gets invoked only if [retry policy][RetryPolicy]'s resolution was not [DoNotRetry][Retry.DoNotRetry].
 *
 * The lowest policy present in the DSL cascade ([HttpClient][com.agoda.ninjato.http.HttpClient]
 * -> [Api][com.agoda.ninjato.Api] -> [Request.Configurator][com.agoda.ninjato.http.Request.Configurator]) is selected for invocation.
 *
 * Fallback policies gives the ability to modify your [request][Request] or provide completely different [request][Request]
 * before attempting a retry.
 */
abstract class FallbackPolicy : Policy<Request>
