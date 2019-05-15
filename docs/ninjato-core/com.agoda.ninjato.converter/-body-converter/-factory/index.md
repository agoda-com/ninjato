[ninjato-core](../../../index.md) / [com.agoda.ninjato.converter](../../index.md) / [BodyConverter](../index.md) / [Factory](./index.md)

# Factory

`interface Factory`

Factory interface for providing converters to and from [body](../../../com.agoda.ninjato.http/-body/index.md).

### Functions

| Name | Summary |
|---|---|
| [requestConverter](request-converter.md) | `abstract fun requestConverter(type: `[`Type`](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html)`): `[`BodyConverter`](../index.md)`<*, `[`Body`](../../../com.agoda.ninjato.http/-body/index.md)`>?`<br>Function that provides converter from custom type to [body](../../../com.agoda.ninjato.http/-body/index.md). |
| [responseConverter](response-converter.md) | `abstract fun responseConverter(type: `[`Type`](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html)`): `[`BodyConverter`](../index.md)`<`[`Body`](../../../com.agoda.ninjato.http/-body/index.md)`, *>?`<br>Function that provides converter from [body](../../../com.agoda.ninjato.http/-body/index.md) to custom type. |
