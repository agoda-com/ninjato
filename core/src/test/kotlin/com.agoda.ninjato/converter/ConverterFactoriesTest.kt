package com.agoda.ninjato.converter

import org.junit.Test
import java.lang.reflect.Type

class ConverterFactoriesTest {

    private val factories = ConverterFactories()

    private val first = object : BodyConverter.Factory {
        override fun requestConverter(type: Type) = null
        override fun responseConverter(type: Type) = null
    }

    private val second = object : BodyConverter.Factory {
        override fun requestConverter(type: Type) = null
        override fun responseConverter(type: Type) = null
    }

    @Test
    fun testResolve() {
        // Arrange
        factories += first
        factories += listOf(second)

        // Act
        val result = factories.resolve()

        // Assert
        assert(result[0] == first)
        assert(result[1] == second)
    }

    @Test
    fun testResolveWithParent() {
        // Arrange
        val parent = ConverterFactories()

        parent += first
        factories += second

        factories.parent = parent

        // Act
        val result = factories.resolve()

        // Assert
        assert(result[0] == second)
        assert(result[1] == first)
    }

}
