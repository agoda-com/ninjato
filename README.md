# Ninjato
[![Bintray version](https://api.bintray.com/packages/agoda/maven/ninjato/images/download.svg)](https://bintray.com/agoda/maven/ninjato)
[![Kotlin version badge](https://img.shields.io/badge/kotlin-1.3.21-blue.svg)](http://kotlinlang.org/)
[![codecov](https://codecov.io/gh/agoda-com/ninjato/branch/master/graph/badge.svg)](https://codecov.io/gh/agoda-com/ninjato)

Flexible and type-safe inline HTTP client for Android and Kotlin

## What is Ninjato?
Ninjato is a library that lets you write simple yet powerful remote HTTP calls, whether it's RESTful services or any other.
But at the same time the library gives you the complete control of the flow.

The library is written in Kotlin and tries to rely on reflection as low as possible. Most of the functionality
is implemented with the help of inlining and reified types inference during compile time.

## Why not Retrofit?
Retrofit is a great library, and people at Square do amazing and very important stuff.
But for us at Agoda, after a long time with Retrofit we started to feel limited with the way how
the library gives us opportunities to customize, intercept and implement more flexible behavior.

## How to use?

### Basic syntax
In a nutshell, Ninjato gives a possibility to execute any type of HTTP call through `Api` class.
As such, you can have several approaches to define and use your remote services:

Extend the `Api` class directly:
```kotlin
class YourApi(client: HttpClient, config: Api.() -> Unit) : Api(client, config) {
    override val baseUrl = "https://yourApi.com"
    
    fun search(query: String): SearchResult = get {
        endpointUrl = "/search"
        parameters { "query" to query }
    }
}
```
Hide implementation behind the interface:
```kotlin
interface YourApi {
    fun updateArticle(article: Article)
}

class YourApiImpl(client: HttpClient, config: Api.() -> Unit) : Api(client, config), YourApi {
    override val baseUrl = "https://yourApi.com"
    
    fun updateArticle(article: Article) = post {
        endpointUrl = "/updateArticle"
        body = article
    }
}
```
Or implement your endpoints right in the interface:
```kotlin
interface YourApi {
    val api: Api
    
    fun putListing(listing: Listing) = api.put {
        endpointUrl = "/putListing"
        body = listing
    }
}
```

There are a lot more ways how you can organize your remote services' code, but we're not going to cover it here.

### Main features
As you already understood from the examples below, Ninjato can infer the type of body and what is expected
to be returned from the request. But there are a lot more features. All of them will be explained in the
sections below with code examples.

#### DSL cascade: `HttpClient`, `Api` and `Request`
Ninjato is structured in a way that allows it's users to aggregate properties and/or behavior of their
remote calls through the DSL cascade. This means that all main entities of library: `HttpClient`, `Api` and `Request`
have the same configuration interface where you can configure your headers, parameters, interceptors, etc.

During the actual call configuration, all of these configurations will be aggregated from **bottom** to **top**,
or replaced, if only one value is allowed.

#### Request body and return type inference
Thanks to Kotlin capabilities, Ninjato infers the type of body being set (if allowed) and expected return type.

Default types of `body` property include:
 - `Body`
 - `String`
 - `ByteArray`

Default types of return value include:
 - `Unit`
 - `Response`
 - `Body`
 - `Stirng`
 - `ByteArray`

For all custom types library has support of `BodyConverter`.

#### BodyConverter
`BodyConverter` is a simple interface with a `convert` function from one to another.
Library uses the provided `BodyConverter.Factory` instances to get the converter for a specific type.
You can provide supplied or your own factory to any level of the DSL cascade:

```kotlin
val client = NinjatoOkHttpClient(okHttpClient) {
    converterFactories += GsonConverterFactory(Gson())
}

val api = YourApi(client) {
    converterFactories += MoshiConverterFactory()
}

val result: SearchResult = api.get {
    endpointUrl = "/get"
    converterFactories += JacksonConverterFactory()
}
```

List of body converters provided via extension artifacts can be found in **Setup** section.

#### Interceptors
Interceptors are instances that are executed prior (`RequestInterceptor`) the request is served by
the HTTP client and after (`ResponseInterceptor`) the response was acquired. Interceptors have the ability
to modify given instance of `Request`/`Response` or even provide completely another instances.

Interceptors can be added to any level of the DSL cascade. The following code can be used also in `Api` classes
and in any call configuration:

```kotlin
val client = NinjatoOkHttpClient(okHttpClient) {
    interceptors += MyRequestInterceptor()
    interceptors += MyResponseInterceptor()
    
    interceptors {
        request { r ->
            Log.d(r)
            r
        }
        
        response { r ->
            Log.d(r)
            r
        }
    }
}
```

#### RetryPolicy
Simply put, `RetryPolicy` is a class that can allow you to decide, whether or not given request should
try again and be executed. You get the `Request` and `Throwable` as input and should return one of the
`Retry` sealed class children: `DoNotRetry`, `WithoutDelay` or `WithDelay`.

Retry policy can be added to any level of the DSL cascade. The following code can be used also in `Api` classes
and in any call configuration:

```kotlin
val client = NinjatoOkHttpClient(okHttpClient) {
    retryPolicy = MyRetryPolicy()
    
    retryPolicy { request, throwable ->
        if (request.retries > 3) Retry.DoNotRetry else Retry.WithoutDelay
    }
}
```

#### FallbackPolicy
In case your `RetryPolicy` allowed a request to be retried, `FallbackPolicy` can help you with modifying
you request before a retry attempt. For example, change the `baseUrl` of your request.

Fallback policy can be added to any level of the DSL cascade. The following code can be used also in `Api` classes
and in any call configuration:

```kotlin
val client = NinjatoOkHttpClient(okHttpClient) {
    fallbackPolicy = MyFallbackPolicy()
    
    fallbackPolicy { request, throwable -> 
        request.also { it.baseUrl = "https://anotherServer.com" }
    }
}
```

#### Headers and query parameters
Headers and url query parameters are also part of the DSL cascade.

Headers and query parameters can be added to any level of the DSL cascade. 
The following code can be used also in `HttpClient` and `Api` classes:

```kotlin
val result: SearchResult = get {
    endpointUrl = "/search"
    headers += "A" to "B"
    
    headers {
        "B" to "C"
        
        cookie {
            "C" to "D"
            expires = 3600
            isSecure = true      
        }
    }
    
    parameters {
        "query" to query
    }
}
```

#### Request.Factory
To add possibility to extend `Request` entity and enrich it with use-case specific properties and/or logic, Ninjato
has a factory that will return instances of `Request` which can be manipulated further in interceptors:

```kotlin
class YourRequestFactory : Request.Factory() {
    override fun create() = YourRequest()
}

class YourRequestInterceptor : RequestInterceptor() {
    fun intercept(request: Request): Request {
        if (request is YourRequest) {
            request.headers["request_id"] = request.id
        }
    }
}

val client = NinjatoOkHttpClient(okHttpClient, YourRequestFactory()) {
    interceptors += YourRequestInterceptor()
}
```

#### Response.Factory
To add possibility to extend `Resposne` entity and enrich it with use-case specific properties and/or logic, Ninjato
has a factory that will return instances of `Response` which can be manipulated further in interceptors:

```kotlin
class YourResponseFactory : Response.Factory() {
    override fun create() = YourResponse()
}

// usage example
class YourResponseInterceptor {
    fun intercept(response: Response): Response {
        if (response is YourResponse) {
            response.token = response.headers["auth_token"]
        }
    }
}

val client = NinjatoOkHttpClient(okHttpClient, YourRequestFactory(), YourResponseFactory()) {
    interceptors += YourRequestFactory()
    interceptors += YourResponseFactory()
}
```

#### Wrappers
In case you prefer to use some sealed class type wrapper, of RxJava, for example, Ninjato has you covered as well.
There are extension artifacts available that are providing extension wrapping functions for your `Api` classes.
Here are few examples:

```kotlin
interface YourApi {
    val api: Api
    
    fun search(query: String): Call<SearchResult> = api.call {
        get {
            endpointUrl = "/serach"
            params { "query" to query }
        }
    }
    
    fun updateArticle(article: Article): Completable = api.completable {
        post {
            endpointUrl = "/articles"
            body = article
        }
    }
    
    fun putListing(listing: Listing): Flowable<Response> = api.flowable {
        put {
            endpointUrl = "/listings"
            body = listing
        }
    }
}
```

List of available extension artifacts can be found in **Setup** section.

#### More
For any additional information please refer to library's documentation.

### Setup
Maven
```xml
<!-- Core library !-->
<dependency>
  <groupId>com.agoda.ninjato</groupId>
  <artifactId>core</artifactId>
  <version>LATEST_VERSION</version>
  <type>pom</type>
</dependency>

<!-- OkHttp 3 client !-->
<dependency>
  <groupId>com.agoda.ninjato</groupId>
  <artifactId>client-okhttp</artifactId>
  <version>LATEST_VERSION</version>
  <type>pom</type>
</dependency>

<!-- Gson body converter !-->
<dependency>
  <groupId>com.agoda.ninjato</groupId>
  <artifactId>converter-gson</artifactId>
  <version>LATEST_VERSION</version>
  <type>pom</type>
</dependency>

<!-- Call wrapper !-->
<dependency>
  <groupId>com.agoda.ninjato</groupId>
  <artifactId>extension-call</artifactId>
  <version>LATEST_VERSION</version>
  <type>pom</type>
</dependency>

<!-- RxJava wrappers !-->
<dependency>
  <groupId>com.agoda.ninjato</groupId>
  <artifactId>extension-rxjava</artifactId>
  <version>LATEST_VERSION</version>
  <type>pom</type>
</dependency>

<!-- RxJava2 wrappers !-->
<dependency>
  <groupId>com.agoda.ninjato</groupId>
  <artifactId>extension-rxjava2</artifactId>
  <version>LATEST_VERSION</version>
  <type>pom</type>
</dependency>
```
or Gradle:
```groovy
repositories {
    jcenter()
}

dependencies {
    // Core library
    implementation 'com.agoda.ninjato:core:LATEST_VERSION'

    // OkHttp 3 client
    implementation 'com.agoda.ninjato:client-okhttp:LATEST_VERSION'

    // Gson body converter
    implementation 'com.agoda.ninjato:converter-gson:LATEST_VERSION'

    // Call wrapper
    implementation 'com.agoda.ninjato:extension-call:LATEST_VERSION'

    // RxJava wrappers
    implementation 'com.agoda.ninjato:extension-rxjava:LATEST_VERSION'

    // RxJava 2 wrappers
    implementation 'com.agoda.ninjato:extension-rxjava2:LATEST_VERSION'
}
```

### Contribution Policy

Ninjato is an open source project, and depends on its users to improve it. We are more than happy
to find you interested in taking the project forward.

Kindly refer to the [Contribution Guidelines](https://github.com/agoda-com/ninjato/blob/master/CONTRIBUTING.md) for detailed information.

### Code of Conduct

Please refer to [Code of Conduct](https://github.com/agoda-com/ninjato/blob/master/CODE_OF_CONDUCT.md) document.

### License

Boots is available under the [Apache License, Version 2.0](https://github.com/agoda-com/ninjato/blob/master/LICENSE).

### Thanks to

* [Unlimity](https://github.com/unlimity) - **Ilya Lim**
* [Yundom](https://github.com/yundom) - **Dennis Hsieh**