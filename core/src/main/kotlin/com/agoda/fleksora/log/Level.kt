package com.agoda.fleksora.log

sealed class Level {
    object Verbose : Level()
    object Info : Level()
    object Debug : Level()
    object Warning : Level()
    object Error : Level()
}
