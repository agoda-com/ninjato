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

// Override
interceptors = RequestInterceptors()
interceptors = listOf(RequestInterceptor(), ResponseInterceptor())
```

#### Body
For the request body, if we want to achieve support of auto-serializing and deserializing as well as support of different
protocols, we should keep body aligned with the standard of HTTP clients:
```kotlin
class Body {
    val mediaType: String
    val body: Array<Byte>
}
```