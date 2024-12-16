package com.skh.stopwatch.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skh.stopwatch.data.StopWatchRecord
import com.skh.stopwatch.util.colorMaker

@Composable
fun StopWatchRecords(records: List<StopWatchRecord>, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Row {
            Text(
                "구간",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(48.dp),
                color = Color.Gray
            )
            Text(
                "구간기록",
                fontSize = 24.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                color = Color.Gray
            )
            Text(
                "전체 시간",
                fontSize = 24.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                color = Color.Gray
            )
        }

        HorizontalDivider()

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {

            items(
                items = records,
                key = { it.interval }
            ) { item ->
                Row {
                    Text(
                        String.format("%02d", item.interval),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(48.dp),
                        color = records.colorMaker(item.interval)
                    )
                    StopWatchLabel(
                        item.splitTime,
                        fontSize = 24.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End,
                        color = Color.Gray
                    )
                    StopWatchLabel(
                        item.overallTime,
                        fontSize = 24.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun StopWatchRecordsPrev() {
    var records = listOf(
        StopWatchRecord(1, 0.15, 0.30),
        StopWatchRecord(2, 3.467, 34.24),
        StopWatchRecord(3, 3.5788, 346.2),
        StopWatchRecord(4, 9.24, 395.32),
//        StopWatchRecord(5, 0.15, 0.30),
//        StopWatchRecord(6, 3.467, 34.24),
//        StopWatchRecord(7, 3.5788, 346.2),
//        StopWatchRecord(8, 0.15, 0.30),
//        StopWatchRecord(9, 3.467, 34.24),
//        StopWatchRecord(10, 3.5788, 346.2),
//        StopWatchRecord(11, 9.24, 395.32),
//        StopWatchRecord(12, 0.15, 0.30),
//        StopWatchRecord(13, 3.467, 34.24),
//        StopWatchRecord(14, 3.5788, 346.2),
//        StopWatchRecord(15, 9.24, 395.32),
//        StopWatchRecord(16, 0.15, 0.30),
//        StopWatchRecord(17, 3.467, 34.24),
//        StopWatchRecord(18, 3.5788, 346.2),
//        StopWatchRecord(19, 0.15, 0.30),
//        StopWatchRecord(20, 3.467, 34.24),
//        StopWatchRecord(21, 3.5788, 346.2),
//        StopWatchRecord(22, 9.24, 395.32),
    )

    StopWatchRecords(records)
}