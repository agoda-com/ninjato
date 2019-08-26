package com.agoda.ninjato.policy

/**
 * Retry policy.
 * This policy gets invoked when request has failed to complete successfully.
 *
 * The lowest policy present in the DSL cascade ([HttpClient][com.agoda.ninjato.http.HttpClient]
 * -> [Api][com.agoda.ninjato.Api] -> [Request.Configurator][com.agoda.ninjato.http.Request.Configurator]) is selected for invocation.
 *
 * Retry policies gives the ability to supply custom logic and decide whether you need to retry executing
 * your requests or not.
 *
 * @see Retry
 */
abstract class RetryPolicy : Policy<Retry>
