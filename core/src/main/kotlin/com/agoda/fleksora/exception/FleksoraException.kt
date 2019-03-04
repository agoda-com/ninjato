package com.agoda.fleksora.exception

class FleksoraException(url: String?, cause: Throwable?) : Throwable("Fleksora couldn't serve your request ${url?.let {"with url $it"} ?: ""}", cause)
