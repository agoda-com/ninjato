# fleksora
Flexible Kotlin-Style open RESTful API

## DSL contract

### Entities

#### Headers
Headers class should be able to add, remove and override headers down the cascade of the DSL.
This is how I imagine it should be:
```kotlin
class Headers {
    val added: Map<String, String>
    val removed: Map<String, String>
    val overridden: Map<String, String>
}
```
Usage in the DSL:
```kotlin
headers += "A" to "B" // add

headers -= "A" // will remove all headers with key A
headers -= "A" to "B" // will remove header with key A and value B

headers = mapOf( // will override values up the cascade with provided
    "A" to "B",
    "C" to "D"
)
```
This behaviour can be achieved using operator overriding and property delegate (for overriding setValue operator).

#### Interceptors
Interceptors should be implemented as currently in v1, with fex additions. We still want to have an interface of
interceptor to enable developers to write custom classes as interceptors, although to enable the DSL capabilities,
we need to introduce several classes: `RequestInterceptor : Interceptor<Request>`, `ResponseInterceptor : Interceptor<Response>`
and `Interceptors`.
This is how I imagine it should be:
```kotlin
class Interceptors {
    val added: List<Interceptor<*>>
    val removed: List<Interceptor<*>>
    val overridden: List<Interceptor<*>>
}
```
Usage in the DSL:
```kotlin
// add
interceptors += RequestInterceptor() 
interceptors += ResponseInterceptor()

// remove
interceptors -= RequestInterceptors()
interceptors -= ResponseInterceptor()

// override
interceptors = RequestInterceptors()
interceptors = listOf(RequestInterceptor(), ResponseInterceptor())
```

#### RetryPolicy
Current implementation is done in a way where you have to wrap your policy around the actual call, which is not the most
optimized way. I suggest to simplify it to a small interface/function that simply takes a `Request` and `Throwable` in and
returns `Pair<Boolean, Long>`. If `true`, the framework will retry to execute the call after given milliseconds or immediately
if the second value is `0L`.
```kotlin
interface RetryPolicy {
    fun retry(request: Request, throwable: Throwable): Pair<Boolean, Long>
}
```

#### FallbackPolicy
Current implementation is done using the `Interceptor` interface, which means that fallback policy does not know why the
request has failed and is very restricted in the behaviour. I suggest to introduce separate interface/function for the
fallback policy that will be invoked if the request has failed and the retry policy has returned `true`.
```kotlin
interface FallbackPolicy {
    fun fallback(request: Request, throwable: Throwable): Request
}
```

#### Default implementation of retries and fallback
Framework should provide basic implementations of `RetryPolicy` and `FallbackPolicy`. I suggest the following: `NoRetryPolicy`
which will always return `false to 0L` as default and `SimpleRetryPolicy(val retries: Int, val delay: Long)` as an option.
As for fallbacks `NoFallbackPolicy` which will always forward the passed instance of request should do.

#### Body
For the request body, if we want to achieve support of auto-serializing and deserializing as well as support of different
protocols, we should keep body aligned with the standard of HTTP clients:
```kotlin
class Body {
    val mediaType: String
    val contentLength: Long
    val body: Array<Byte>
}
```

#### Request
Request entity can be pretty much reused as is in our v1 version with slight changes:
 - Remove `id` and `requestId` properties (`requestId` can be added in child class, `AgodaRequest` for example)
 - Remove url manipulation functions (url will be set in the DSL)
 - Make constructor empty
 - All properties should be `var`
 - Replace current `String` body with `Body` class

#### Response
Response entity can be pretty much reused as is in our v1 version with slights changes:
 - Make the class `open`
 - Make constructor empty
 - All properties should be `var`
 - Replace current `String` body with `Body` class
 
#### Request.Factory
To add possibility to extend `Request` entity and enrich it with use-case specific properties and/or logic, framework
needs a factory that will return instances of `Request` which can be manipulated further in interceptors:
```kotlin
open class Request {
    open class Factory {
        fun create(): Request = Request()
    }
}

// usage example
class RequestInterceptor {
    fun intercept(request: Request): Request {
        if (request is AgodaRequest) {
            request.headers["request_id"] = request.id
        }
    }
}
```

#### Response.Factory
To add possibility to extend `Resposne` entity and enrich it with use-case specific properties and/or logic, framework
needs a factory that will return instances of `Response` which can be manipulated further in interceptors:
```kotlin
open class Response {
    open class Factory {
        fun create(): Response = Response()
    }
}

// usage example
class ResponseInterceptor {
    fun intercept(response: Response): Response {
        if (response is AgodaResponse) {
            response.token = response.headers["auth_token"]
        }
    }
}
```

#### Commons (help with the naming)
In order to reduce boilerplate code in the framework itself, there should be `Commons` interface with default implementations
of functions and properties to grant the access to the common parts of the library's DSL to all cascade components: `HttpClient`, 
`Api` and `Call`. In general it should just add properties of the entities that can be enriched or overridden in the cascade:
```kotlin
interface Commons {
    val headers: Headers
    val interceptors: Interceptors
    var retryPolicy: RetryPolicy
    var fallbackPolicy: FallbackPolicy
}
```

#### HttpClient
This entity should implement the `Commons` interface and provide a function to actually execute Http calls. Current version
can be reused with simplifications:
```kotlin
abstract class HttpClient : Commons {
    internal var logger: Logger?
    
    abstract fun execute(request: Request): Response
}

// Builder pattern
fun provideHttpClient(dependencies: ...): HttpClient {
    return HttpClient.build(OkHttpFleksoraClient(okHttpClient)) {
        logger = AndroidLogger()
    }
}
```

#### Api
This entity is much more interesting. It should differ very much from the current implementation. First of all, empty constructor
so that we don't have to expose any component outside of the DI function where instance is created through some sort of
the builder pattern. Second, there is no need for any abstract properties you need to override except `baseUrl`:
```kotlin
abstract class Api : Commons {
    abstract val baseUrl: String
    
    internal var serializer: Serializer?
    internal var logger: Logger?
    
    inline fun get()
    inline fun put()
    inline fun post()
    inline fun patch()
    inline fun delete()
    inline fun head()
}

fun provideApi(dependencies: ...): Api {
    return Api.build(AgodaApi()) {
        httpClient = providedClient
        serializer = AgodaSerializer()
        logger = AndroidLogger()
    }
}
```