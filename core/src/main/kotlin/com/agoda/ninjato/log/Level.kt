package com.agoda.ninjato.log

sealed class Level(val id: Int) {
    object Verbose : Level(0)
    object Info : Level(1)
    object Debug : Level(2)
    object Warning : Level(3)
    object Error : Level(4)
}
