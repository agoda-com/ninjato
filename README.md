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
This behaviour can be achieved using operator overriding.