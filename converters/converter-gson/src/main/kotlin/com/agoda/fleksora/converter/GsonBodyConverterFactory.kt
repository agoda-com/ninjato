package com.agoda.fleksora.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class GsonBodyConverterFactory(private val gson: Gson = Gson()) : BodyConverter.Factory {
    override fun requestConverter(type: Type) = GsonRequestBodyConverter(gson, gson.getAdapter(TypeToken.get(type)))
    override fun responseConverter(type: Type) = GsonResponseBodyConverter(gson, gson.getAdapter(TypeToken.get(type)))
}
