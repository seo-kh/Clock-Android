package com.skh.stopwatch.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun StopWatchLabel(
    text: String = "00:00.00",
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    fontSize: TextUnit = 48.sp,
    fontFamily: FontFamily = FontFamily.Monospace,
    color: Color = Color.Black
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = textAlign,
        fontSize = fontSize,
        fontFamily = fontFamily,
        color = color
    )
}

@Composable
fun StopWatchLabel(
    duration: Double = 0.15,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    fontSize: TextUnit = 48.sp,
    fontFamily: FontFamily = FontFamily.Monospace,
    color: Color = Color.Black
) {
    val totalSeconds = duration.toInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    val milliseconds = ((duration - totalSeconds) * 100).toInt()
    val formattedDuration = String.format("%02d:%02d.%02d", minutes, seconds, milliseconds)

    Text(
        text = formattedDuration,
        modifier = modifier,
        textAlign = textAlign,
        fontSize = fontSize,
        fontFamily = fontFamily,
        color = color
    )
}

@Preview
@Composable
private fun Label(modifier: Modifier = Modifier) {
    StopWatchLabel(duration = 3.45)
}

