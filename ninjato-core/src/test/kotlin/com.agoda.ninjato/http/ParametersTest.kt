package com.agoda.ninjato.http

import org.junit.Test

class ParametersTest {

    private val parameters = Parameters()

    @Test
    fun testResolve() {
        // Arrange
        parameters += "A" to "B"
        parameters { "B" to "C" }

        // Act
        val resolved = parameters.resolve()

        // Assert
        assert(resolved["A"] == "B")
        assert(resolved["B"] == "C")
    }

    @Test
    fun testResolveWithParent() {
        // Arrange
        val parent = Parameters().also { it {
            "A" to "B"
            "C" to "D"
        } }

        parameters.parent = parent

        parameters {
            "A" to "C"
            "B" to "C"
        }

        // Act
        val resolved = parameters.resolve()

        // Assert
        assert(resolved["A"] == "C")
        assert(resolved["B"] == "C")
        assert(resolved["C"] == "D")
    }


}
