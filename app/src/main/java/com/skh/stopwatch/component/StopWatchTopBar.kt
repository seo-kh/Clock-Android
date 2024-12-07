package com.skh.stopwatch.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopWatchTopBar(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
        ),
        title = {},
        actions = {
            Row(
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onClick,
                ) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more")
                }
            }
        }
    )
}

@Preview
@Composable
private fun TopBar() {
    StopWatchTopBar(onClick = {})
}
