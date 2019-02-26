package com.agoda.fleksora.http

import com.agoda.fleksora.dsl.Commons
import com.agoda.fleksora.intercept.Interceptors
import com.agoda.fleksora.policy.FallbackPolicy
import com.agoda.fleksora.policy.RetryPolicy
import com.agoda.fleksora.serial.Serializer
import com.agoda.fleksora.serial.SerializerFactories

open class Request {

    var retries = 0

    open class Factory {

    }

    open class Builder : Commons {
        override val headers = Headers()
        override val interceptors = Interceptors()
        override val serializerFactories = SerializerFactories()

        override var retryPolicy: RetryPolicy? = null
        override var fallbackPolicy: FallbackPolicy? = null

        var endpointUrl: String? = null
        var fullUrl: String? = null

        open fun build(): Request { return Request() }

        open class WithBody(factories: List<Serializer.Factory>) : Builder() {
            var body: Any? by Serializer.Factory.Delegate(factories)

            override fun build(): Request { return super.build() }
        }
    }
}
