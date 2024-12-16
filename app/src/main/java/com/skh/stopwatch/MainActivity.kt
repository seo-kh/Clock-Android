package com.skh.stopwatch

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var overallTime: Double by rememberSaveable { mutableStateOf(0.0) }
    var splitTime: Double by rememberSaveable { mutableStateOf(0.0) }
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
            Spacer(Modifier.height(if (records.isEmpty()) 88.dp else 48.dp))

            // STOPWATCH LABEL
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
            ) {
                StopWatchLabel(time = overallTime)
            }

            // STOPWATCH RECORD LIST
            if (records.isNotEmpty()) {
                StopWatchLabel(splitTime, fontSize = 24.sp, color = Color.Gray)

                Box(
                    modifier = Modifier
                        .padding(top = if (records.isEmpty()) 0.dp else 32.dp)
                ) {
                    StopWatchRecords(records)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // STOPWATCH BUTTON GROUP
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                StopWatchButtonGroup(
                    startStopWatch = { overallTime += 0.03; splitTime += 0.03 },
                    clearStopWatch = { overallTime = 0.0; records.clear(); splitTime = 0.0 },
                    updateRecord = { records.add(splitTime, overallTime); splitTime = 0.0 }
                )
            }
        }
    }
}