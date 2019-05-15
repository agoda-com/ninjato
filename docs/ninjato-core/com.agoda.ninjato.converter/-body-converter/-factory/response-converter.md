[ninjato-core](../../../index.md) / [com.agoda.ninjato.converter](../../index.md) / [BodyConverter](../index.md) / [Factory](index.md) / [responseConverter](./response-converter.md)

# responseConverter

`abstract fun responseConverter(type: `[`Type`](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html)`): `[`BodyConverter`](../index.md)`<`[`Body`](../../../com.agoda.ninjato.http/-body/index.md)`, *>?`

Function that provides converter from [body](../../../com.agoda.ninjato.http/-body/index.md) to custom type.

### Parameters

`type` - type that needs to be converted to.

**Return**
converter from body to provided type, `null` if your factory cannot serve provided type.

