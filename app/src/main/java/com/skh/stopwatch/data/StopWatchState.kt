package com.skh.stopwatch.data

import androidx.compose.ui.graphics.Color

enum class StopWatchState {
    INTRO,
    PROGRESS,
    PAUSE;

    data class ButtonConfig(val title: String, val containerColor: Color, val contentColor: Color)

    fun getButtonConfig(): List<ButtonConfig> {
        return when (this) {
            INTRO -> listOf(
                ButtonConfig("구간 기록", Color(0xFFF5F5F5), Color.Gray),
                ButtonConfig("시작", Color(0xFF5E35B1), Color.White),
            )
            PROGRESS -> listOf(
                ButtonConfig("구간 기록", Color(0xFFF5F5F5), Color.DarkGray),
                ButtonConfig("중지", Color(0xFFD50000), Color.White),
            )
            PAUSE -> listOf(
                ButtonConfig("초기화", Color(0xFFF5F5F5), Color.DarkGray),
                ButtonConfig("계속", Color(0xFF5E35B1), Color.White),
            )
        }
    }
}