package com.agoda.ninjato.http

import org.junit.Test
import java.lang.IllegalStateException

class CookiesTest {

    private val cookies = Cookies()

    @Test
    fun testResolveFull() {
        // Arrange
        cookies {
            "a" to "b"
            expires = 1
            maxAge = 1
            path = "/test"
            domain = "agoda.com"
            isSecure = true
            isHttpOnly = true
        }

        // Act
        val result = cookies.resolve()

        // Assert
        assert(result == "a=b; expires=Thu, 01-Jan-1970 00:00:01 GMT; max-age=1; path=/test; domain=agoda.com; Secure; HttpOnly")
    }

    @Test
    fun testResolveValueOnly() {
        // Arrange
        cookies { "a" to "b" }

        // Act
        val result = cookies.resolve()

        // Assert
        assert(result == "a=b")
    }

    @Test(expected = IllegalStateException::class)
    fun testDoubleValueSetting() {
        // Act
        cookies {
            "a" to "b"
            "a" to "b"
        }
    }

    @Test(expected = IllegalStateException::class)
    fun testResolveWithoutValue() {
        // Arrange
        cookies {
            expires = 1
            maxAge = 1
        }

        // Act
        val result = cookies.resolve()
    }

}
