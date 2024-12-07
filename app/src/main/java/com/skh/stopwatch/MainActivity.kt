package com.skh.stopwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.skh.stopwatch.component.StopWatchLabel
import com.skh.stopwatch.component.StopWatchTopBar
import com.skh.stopwatch.component.StopWatchButtonGroup
import com.skh.stopwatch.ui.theme.StopWatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StopWatchTheme {

            }
        }
    }
}

@Preview
@Composable
fun StopWatch() {
    val (duration, setDuration) = remember { mutableStateOf(0.0) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            StopWatchTopBar(onClick = {})
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(paddingValues)
        ) {
            // STOPWATCH LABEL
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(
                        // TODO: NO LIST - 0.4f, LIST: - 0.3f
                        0.4f
                    )
            ) {
                StopWatchLabel(duration = duration)
            }

            // TODO: RECORD LIST
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(
                        // TODO: NO LIST - 0.4f, LIST: - 0.5f
                        0.4f
                    )
            )

            // BUTTON GROUP
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.2f)
            ) {
                StopWatchButtonGroup(setDuration = setDuration)
            }
        }
    }

}