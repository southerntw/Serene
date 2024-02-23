package com.southerntw.safespace.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.southerntw.safespace.ui.theme.MoodGreen
import com.southerntw.safespace.ui.theme.MoodGrey
import com.southerntw.safespace.ui.theme.MoodRed
import com.southerntw.safespace.ui.theme.White

@Composable
fun MoodDisplay(modifier: Modifier = Modifier, userMood: String) {
    val backgroundColor = when (userMood) {
        "Excellent" -> MoodGreen
        "Unknown" -> MoodGrey
        "Bad" -> MoodRed
        else -> Color.Transparent // Return a default color for the else branch
    }

    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(backgroundColor)
    ) {
        Text(userMood, color = White, style = MaterialTheme.typography.bodyLarge)
    }
}