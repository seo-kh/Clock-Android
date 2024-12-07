package com.skh.stopwatch.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun StopWatchLabel(text: String = "00:00.00", modifier: Modifier = Modifier) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 48.sp,
        fontFamily = FontFamily.Monospace
    )
}

@Composable
fun StopWatchLabel(duration: Double = 0.15, modifier: Modifier = Modifier) {
    val formattedDuration: String = String.format("%05.2f", duration)

    Text(
        text = formattedDuration,
        textAlign = TextAlign.Center,
        fontSize = 48.sp,
        fontFamily = FontFamily.Monospace
    )
}

@Preview
@Composable
private fun Label(modifier: Modifier = Modifier) {
    StopWatchLabel(duration = 3.45)
}
