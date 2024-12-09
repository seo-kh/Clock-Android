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
import java.util.Timer
import kotlin.concurrent.timer

class StopWatchTimer {
    private var timer: Timer? = null

    private constructor()

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

@Composable
fun StopWatchButtonGroup(
    startStopWatch: () -> Unit,
    clearStopWatch: () -> Unit,
    stopWatchTimer: StopWatchTimer = StopWatchTimer.INSTANCE,
    modifier: Modifier = Modifier
) {
    Log.d("StopWatch", "StopWatchButtonGroup")
    var stopWatchState by rememberSaveable { mutableStateOf(StopWatchState.INTRO) }

    val buttonConfig = stopWatchState.getButtonConfig()
    val featureButtonConfig = buttonConfig[0]
    val timerButtonConfig = buttonConfig[1]

    StopWatchButtonGroupComponent(
        featureTitle = featureButtonConfig.title,
        featureAction = {
            when (stopWatchState) {
                StopWatchState.INTRO -> {}
                StopWatchState.PAUSE -> {
                    clearStopWatch()
                }
                StopWatchState.PROGRESS -> {
                    // DURATION LIST ADD
                }
            }

            // STATE CHANGE
            stopWatchState = when (stopWatchState) {
                StopWatchState.PAUSE -> StopWatchState.INTRO
                else -> stopWatchState
            }
        },
        featureContainerColor = featureButtonConfig.containerColor,
        featureContentColor = featureButtonConfig.contentColor,
        featureActionEnabled = stopWatchState != StopWatchState.INTRO,
        timerTitle = timerButtonConfig.title,
        timerAction = {
            // STOPWATCH ON or OFF
            when (stopWatchState) {
                StopWatchState.INTRO -> {
                    // TIMER ON
                    stopWatchTimer
                        .start(period = 30) {
                            startStopWatch()
                        }

                }
                StopWatchState.PROGRESS -> {
                    // TIMER OFF
                    stopWatchTimer
                        .stop()

                }
                StopWatchState.PAUSE -> {
                    // TIMER ON
                    stopWatchTimer
                        .start(period = 30) {
                            startStopWatch()
                        }
                }
            }

            // STATE CHANGE
            stopWatchState = when (stopWatchState) {
                StopWatchState.INTRO -> StopWatchState.PROGRESS
                StopWatchState.PROGRESS -> StopWatchState.PAUSE
                StopWatchState.PAUSE -> StopWatchState.PROGRESS
            }

        },
        timerContainerColor = timerButtonConfig.containerColor,
        timerContentColor = timerButtonConfig.contentColor
    )
}

@Composable
fun StopWatchButtonGroupComponent(
    featureTitle: String,
    featureAction: () -> Unit,
    featureContainerColor: Color,
    featureContentColor: Color,
    featureActionEnabled: Boolean = true,
    timerTitle: String,
    timerAction: () -> Unit,
    timerContainerColor: Color,
    timerContentColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            enabled = featureActionEnabled,
            modifier = Modifier.width(120.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = featureContainerColor,
                contentColor = featureContentColor
            ),
            onClick = featureAction
        ) {
            Text(
                featureTitle,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            modifier = Modifier.width(120.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = timerContainerColor,
                contentColor = timerContentColor
            ),
            onClick = timerAction
        ) {
            Text(
                timerTitle,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun introButtonGroup() {
    StopWatchButtonGroup(startStopWatch = {}, clearStopWatch = {})
}

@Preview
@Composable
private fun progressButtonGroup() {
    StopWatchButtonGroup(startStopWatch = {}, clearStopWatch = {})
}
@Preview
@Composable
private fun pauseButtonGroup() {
    StopWatchButtonGroup(startStopWatch = {}, clearStopWatch = {})
}