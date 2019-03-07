[core](../../../index.md) / [com.agoda.ninjato.converter](../../index.md) / [BodyConverter](../index.md) / [Factory](index.md) / [requestConverter](./request-converter.md)

# requestConverter

`abstract fun requestConverter(type: `[`Type`](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html)`): `[`BodyConverter`](../index.md)`<*, `[`Body`](../../../com.agoda.ninjato.http/-body/index.md)`>?`

Function that provides converter from custom type to [body](../../../com.agoda.ninjato.http/-body/index.md).

### Parameters

`type` - type that needs to be converted from.

**Return**
converter from provided type to body, `null` if your factory cannot serve provided type.

