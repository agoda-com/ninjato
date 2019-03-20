package com.agoda.ninjato.policy.impl

import com.agoda.ninjato.http.Request
import com.agoda.ninjato.policy.Retry
import org.junit.Test
import java.lang.IllegalStateException

class DefaultRetryPolicyTest {

    private val policy = DefaultRetryPolicy(1, 0)

    @Test
    fun testRetry() {
        // Arrange
        val request = Request()

        // Act
        val result = policy.evaluate(request, IllegalStateException())

        // Assert
        assert(result is Retry.WithDelay)
    }

    @Test
    fun testDoNotRetry() {
        // Arrange
        val request = Request().also { it.retries = 1 }

        // Act
        val result = policy.evaluate(request, IllegalStateException())

        // Assert
        assert(result is Retry.DoNotRetry)
    }

}
