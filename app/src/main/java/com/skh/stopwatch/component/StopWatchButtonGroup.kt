package com.skh.stopwatch.component

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skh.stopwatch.data.StopWatchState

@Composable
fun StopWatchButtonGroup(
    setDuration: (Double) -> Unit,
    modifier: Modifier = Modifier
) {
    var stopWatchState by remember { mutableStateOf(StopWatchState.INTRO) }

    val buttonConfig = stopWatchState.getButtonConfig()
    val featureButtonConfig = buttonConfig[0]
    val timerButtonConfig = buttonConfig[1]

    StopWatchButtonGroup(
        featureTitle = featureButtonConfig.title,
        featureAction = {
            // STATE CHANGE
            stopWatchState = when (stopWatchState) {
                StopWatchState.PAUSE -> StopWatchState.INTRO
                else -> stopWatchState
            }
            // DURATION LIST ADD
        },
        featureContainerColor = featureButtonConfig.containerColor,
        featureContentColor = featureButtonConfig.contentColor,
        timerTitle = timerButtonConfig.title,
        timerAction = {
            // STATE CHANGE
            stopWatchState = when (stopWatchState) {
                StopWatchState.INTRO -> StopWatchState.PROGRESS
                StopWatchState.PROGRESS -> StopWatchState.PAUSE
                StopWatchState.PAUSE -> StopWatchState.PROGRESS
            }

            // STOPWATCH ON or OFF
        },
        timerContainerColor = timerButtonConfig.containerColor,
        timerContentColor = timerButtonConfig.contentColor
    )
}

@Composable
fun StopWatchButtonGroup(
    featureTitle: String,
    featureAction: () -> Unit,
    featureContainerColor: Color,
    featureContentColor: Color,
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
            modifier = Modifier.width(120.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = featureContainerColor,
                contentColor = featureContentColor
            ),
            onClick = featureAction
        ) {
            Text(featureTitle)
        }

        Button(
            modifier = Modifier.width(120.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = timerContainerColor,
                contentColor = timerContentColor
            ),
            onClick = timerAction
        ) {
            Text(timerTitle)
        }
    }
}

@Preview
@Composable
private fun introButtonGroup() {
    StopWatchButtonGroup(setDuration = { })
}

@Preview
@Composable
private fun progressButtonGroup() {
    StopWatchButtonGroup(setDuration = { })
}
@Preview
@Composable
private fun pauseButtonGroup() {
    StopWatchButtonGroup(setDuration = { })
}