package com.agoda.ninjato.exception

class NinjatoException(url: String?, cause: Throwable?) : Throwable("Ninjato couldn't serve your request ${url?.let {"with url $it"} ?: ""}", cause)
