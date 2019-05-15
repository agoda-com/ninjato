package com.agoda.ninjato.http

import org.junit.Test

class HeadersTest {

    private val headers = Headers()

    @Test
    fun testResolve() {
        // Arrange
        headers += "A" to "B"

        headers {
            "B" to "C"
            "C" to listOf("D", "E")
        }

        headers {
            cookie {
                "a" to "b"
                isSecure = true
            }
        }

        // Act
        val resolved = headers.resolve()

        // Assert
        assert(resolved["A"]!![0] == "B")
        assert(resolved["B"]!![0] == "C")
        assert(resolved["C"]!![0] == "D")
        assert(resolved["C"]!![1] == "E")
        assert(resolved["Cookie"]!![0] == "a=b; Secure")
    }

    @Test
    fun testResolveWithParent() {
        // Arrange
        val parent = Headers().also { it { "A" to "B" } }

        headers.parent = parent

        headers {
            "A" to "C"
            "B" to "C"
        }

        // Act
        val resolved = headers.resolve()

        // Assert
        assert(resolved["A"]!![0] == "B")
        assert(resolved["A"]!![1] == "C")
        assert(resolved["B"]!![0] == "C")
    }
}
