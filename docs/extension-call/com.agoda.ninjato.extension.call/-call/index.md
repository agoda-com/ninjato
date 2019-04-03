[extension-call](../../index.md) / [com.agoda.ninjato.extension.call](../index.md) / [Call](./index.md)

# Call

`sealed class Call<T>`

Wrapper class for API calls. Implemented as sealed class with [Success](-success/index.md) and [Failure](-failure/index.md) subclasses.

NOTE: this wrapper is not asynchronous.

NOTE: this wrapper will be returned as [Success](-success/index.md) if [Response](#) entity
was requested even if remote server returned non 200 response code.

### Types

| Name | Summary |
|---|---|
| [Failure](-failure/index.md) | `class Failure<T> : `[`Call`](./index.md)`<`[`T`](-failure/index.md#T)`>` |
| [Success](-success/index.md) | `class Success<T> : `[`Call`](./index.md)`<`[`T`](-success/index.md#T)`>` |

### Inheritors

| Name | Summary |
|---|---|
| [Failure](-failure/index.md) | `class Failure<T> : `[`Call`](./index.md)`<`[`T`](-failure/index.md#T)`>` |
| [Success](-success/index.md) | `class Success<T> : `[`Call`](./index.md)`<`[`T`](-success/index.md#T)`>` |
