package com.skh.stopwatch.component

import android.util.Log
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skh.stopwatch.data.StopWatchState
import com.skh.stopwatch.util.StopWatchTimer
import java.util.Timer
import kotlin.concurrent.timer


@Composable
fun StopWatchButtonGroup(
    startStopWatch: () -> Unit,
    clearStopWatch: () -> Unit,
    updateRecord: () -> Unit,
    modifier: Modifier = Modifier
) {
    var stopWatchState by rememberSaveable { mutableStateOf(StopWatchState.INTRO) }

    val buttonConfig = stopWatchState.getButtonConfig()
    val featureButtonConfig = buttonConfig[0]
    val timerButtonConfig = buttonConfig[1]

    Row(
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // RECORD BUTTON
        StopWatchButton(
            featureButtonConfig,
            enabled = stopWatchState != StopWatchState.INTRO,
            modifier = Modifier.width(120.dp)
        ) {

            when (stopWatchState) {
                StopWatchState.INTRO -> {}

                // RECORD
                StopWatchState.PROGRESS -> {
                    updateRecord()
                }
                StopWatchState.PAUSE -> {
                    // CLEAR
                    clearStopWatch()
                    stopWatchState = StopWatchState.INTRO
                }
            }
        }

        // TIMER BUTTON
        StopWatchButton(
            timerButtonConfig,
            modifier = Modifier.width(120.dp)
        ) {
            // state action
            stopWatchState.timerAction(onComplete = startStopWatch)

            // state changed
            stopWatchState = stopWatchState.nextState()
        }
    }
}


@Preview
@Composable
private fun introButtonGroup() {
    StopWatchButtonGroup(startStopWatch = {}, clearStopWatch = {}, updateRecord = {})
}

@Preview
@Composable
private fun progressButtonGroup() {
    StopWatchButtonGroup(startStopWatch = {}, clearStopWatch = {}, updateRecord = {})
}
@Preview
@Composable
private fun pauseButtonGroup() {
    StopWatchButtonGroup(startStopWatch = {}, clearStopWatch = {}, updateRecord = {})
}