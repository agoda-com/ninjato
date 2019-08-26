package com.agoda.ninjato.policy

/**
 * Resolutions of the [retry policies][RetryPolicy]
 */
sealed class Retry {
    /**
     * This resolution indicates that there is no need to attempt retries.
     * Occurred exception will be thrown down the call stack.
     */
    object DoNotRetry : Retry()

    /**
     * This resolution indicates that library should attempt retry immediately.
     * Occurred exception will be suppressed.
     */
    object WithoutDelay : Retry()

    /**
     * This resolution indicates that library should attempt retry after a delay.
     * Occurred exception will be suppressed.
     *
     * @param delay lambda that should delay the call. By default it invokes `Thread.sleep(1000)`.
     */
    class WithDelay(val delay: () -> Unit = { Thread.sleep(1000) }) : Retry()
}
