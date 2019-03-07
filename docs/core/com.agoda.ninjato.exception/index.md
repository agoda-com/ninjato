[core](../index.md) / [com.agoda.ninjato.exception](./index.md)

## Package com.agoda.ninjato.exception

### Exceptions

| Name | Summary |
|---|---|
| [HttpException](-http-exception/index.md) | `class HttpException : `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)<br>This exception is thrown when [request](../com.agoda.ninjato.http/-request/index.md) returns error code and the inferred (request) return type is not [response](../com.agoda.ninjato.http/-response/index.md). |
| [MissingBodyException](-missing-body-exception/index.md) | `class MissingBodyException : `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)<br>This exception is thrown when [method](../com.agoda.ninjato.http/-method/index.md) of [request](../com.agoda.ninjato.http/-request/index.md) requires body to be present but cannot be found by the library. |
| [MissingConverterException](-missing-converter-exception/index.md) | `class MissingConverterException : `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)<br>This exception is thrown when library cannot acquire [converter](../com.agoda.ninjato.converter/-body-converter/index.md) from the provided [factories](../com.agoda.ninjato.converter/-body-converter/-factory/index.md) and thus is unable to convert your provided value to [body](../com.agoda.ninjato.http/-body/index.md). |
