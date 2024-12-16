package com.skh.stopwatch.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.snapshots.SnapshotStateList

data class StopWatchRecord(
    val interval: Int,
    val splitTime: Double,
    val overallTime: Double
) {
    companion object {
        val saver = run {
            val intervalKey = "interval"
            val splitTimeKey = "splitTime"
            val overallTimeKey = "overallTime"

            Saver<SnapshotStateList<StopWatchRecord>, List<Map<String, Any>>>(
                save = { records ->
                    records.map { record ->
                        mapOf(
                            intervalKey to record.interval,
                            splitTimeKey to record.splitTime,
                            overallTimeKey to record.overallTime
                        )
                    }
                },
                restore = { savedRecords ->
                    var records: SnapshotStateList<StopWatchRecord> = mutableStateListOf()

                    savedRecords
                        .forEach { savedRecord ->
                            val record = StopWatchRecord(
                                interval = savedRecord[intervalKey] as Int,
                                splitTime = savedRecord[splitTimeKey] as Double,
                                overallTime = savedRecord[overallTimeKey] as Double
                            )

                            records.add(record)
                        }

                    records
                }
            )
        }
    }
}

