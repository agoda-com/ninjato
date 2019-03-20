package com.agoda.ninjato.dsl

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.CLASS

@DslMarker
@Target(CLASS)
@Retention(SOURCE)
annotation class NinjatoDslMarker
