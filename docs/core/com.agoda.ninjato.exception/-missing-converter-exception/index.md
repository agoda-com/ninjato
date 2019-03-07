[core](../../index.md) / [com.agoda.ninjato.exception](../index.md) / [MissingConverterException](./index.md)

# MissingConverterException

`class MissingConverterException : `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)

This exception is thrown when library cannot acquire [converter](../../com.agoda.ninjato.converter/-body-converter/index.md)
from the provided [factories](../../com.agoda.ninjato.converter/-body-converter/-factory/index.md) and thus is unable
to convert your provided value to [body](../../com.agoda.ninjato.http/-body/index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MissingConverterException(url: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>This exception is thrown when library cannot acquire [converter](../../com.agoda.ninjato.converter/-body-converter/index.md) from the provided [factories](../../com.agoda.ninjato.converter/-body-converter/-factory/index.md) and thus is unable to convert your provided value to [body](../../com.agoda.ninjato.http/-body/index.md). |

### Properties

| Name | Summary |
|---|---|
| [type](type.md) | `val type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [url](url.md) | `val url: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
