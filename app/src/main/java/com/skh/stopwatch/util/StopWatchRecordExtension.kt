package com.skh.stopwatch.util

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import com.skh.stopwatch.data.StopWatchRecord

fun List<StopWatchRecord>.colorMaker(interval: Int): Color {
    val minInterval = this
        .minBy { it.splitTime }
        .interval

    val maxInterval = this
        .maxBy { it.splitTime }
        .interval

    return when (interval) {
        minInterval -> Color.Blue
        maxInterval -> Color.Red
        else -> Color.Gray
    }
}

fun SnapshotStateList<StopWatchRecord>.add(splitTime: Double, overallTime: Double) {
    val interval = this.size + 1
    val record = StopWatchRecord(interval, splitTime, overallTime)
    this.add(index = 0, element = record)
}
