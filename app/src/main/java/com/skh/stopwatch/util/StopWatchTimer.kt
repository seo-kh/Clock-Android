package com.skh.stopwatch.util

import java.util.Timer
import kotlin.concurrent.timer

class StopWatchTimer private constructor() {
    private var timer: Timer? = null

    fun start(period: Long, callback: () -> Unit) {
        this.timer = timer(period = period) {
            callback()
        }
    }

    fun stop() {
        this.timer?.cancel()
        this.timer = null
    }

    companion object {
        val INSTANCE = StopWatchTimer()
    }
}
