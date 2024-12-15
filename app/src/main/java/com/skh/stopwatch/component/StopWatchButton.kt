package com.skh.stopwatch.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.skh.stopwatch.data.StopWatchState

@Composable
fun StopWatchButton(
    buttonConfig: StopWatchState.ButtonConfig,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    StopWatchButton(
        title = buttonConfig.title,
        containerColor = buttonConfig.containerColor,
        contentColor = buttonConfig.contentColor,
        modifier = modifier,
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun StopWatchButton(
    title: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Bold,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        onClick = onClick
    ) {
        Text(
            title,
            fontWeight = fontWeight
        )
    }
}