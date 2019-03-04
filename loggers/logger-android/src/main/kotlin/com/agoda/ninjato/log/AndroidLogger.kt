package com.agoda.ninjato.log

import android.util.Log
import com.agoda.ninjato.log.Level.*

/**
 * Android implementation of [Logger].
 *
 * Simply translates log calls to Android's [Log].
 * @param level minimal log level to output
 */
class AndroidLogger(private val level: Level) : Logger {

    override fun log(level: Level, message: String) {
        if (level.id >= this.level.id) {
            when (level) {
                Verbose -> Log.v(LOG_TAG, message)
                Debug -> Log.d(LOG_TAG, message)
                Info -> Log.i(LOG_TAG, message)
                Warning -> Log.w(LOG_TAG, message)
                Error -> Log.e(LOG_TAG, message)
            }
        }
    }

    override fun log(level: Level, message: String, throwable: Throwable) {
        if (level.id >= this.level.id) {
            when (level) {
                Verbose -> Log.v(LOG_TAG, message, throwable)
                Debug -> Log.d(LOG_TAG, message, throwable)
                Info -> Log.i(LOG_TAG, message, throwable)
                Warning -> Log.w(LOG_TAG, message, throwable)
                Error -> Log.e(LOG_TAG, message, throwable)
            }
        }
    }

    companion object {
        const val LOG_TAG = "Ninjato"
    }

}
