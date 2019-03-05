package com.agoda.ninjato.policy

sealed class Retry {
    object DoNotRetry : Retry()
    object WithoutDelay : Retry()
    class WithDelay(val delay: () -> Unit = { Thread.sleep(1000) }) : Retry()
}
