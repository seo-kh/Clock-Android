package com.skh.stopwatch

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.skh.stopwatch.component.StopWatchLabel
import com.skh.stopwatch.component.StopWatchTopBar
import com.skh.stopwatch.component.StopWatchButtonGroup
import com.skh.stopwatch.component.StopWatchRecords
import com.skh.stopwatch.data.StopWatchRecord
import com.skh.stopwatch.ui.theme.StopWatchTheme
import com.skh.stopwatch.util.add

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("StopWatch", "MainActivity")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StopWatchTheme {
                StopWatch()
            }
        }
    }
}

@Preview
@Composable
fun StopWatch() {
    var duration: Double by rememberSaveable { mutableStateOf(0.0) }
    val records = rememberSaveable(saver = StopWatchRecord.saver) { mutableStateListOf<StopWatchRecord>() }

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
                        if (records.isEmpty()) 0.4f else 0.3f
                    )
            ) {
                StopWatchLabel(duration = duration)
            }

            // TODO: RECORD LIST
            if (records.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(
                            if (records.isEmpty()) 0.4f else 0.5f
                        )
                ) {
                    StopWatchRecords(records)
                }
            }

            // BUTTON GROUP
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.2f)
            ) {
                StopWatchButtonGroup(
                    startStopWatch = { duration += 0.03 },
                    clearStopWatch = { duration = 0.0; records.clear() },
                    updateRecord = { records.add(duration) }
                )
            }
        }
    }
}