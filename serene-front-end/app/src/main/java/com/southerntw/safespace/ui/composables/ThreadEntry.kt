package com.southerntw.safespace.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.southerntw.safespace.ui.theme.AlmostBlack

@Composable
fun ThreadEntry(modifier: Modifier = Modifier, threadName: String, threadDescription: String, onClick: () -> Unit) {
    Box(modifier = modifier.fillMaxWidth().height(98.dp).clickable { onClick() }.drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = 1.dp.toPx()
        )
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1.dp.toPx()
        )})
    {
        Box(contentAlignment = Alignment.CenterStart, modifier = modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(threadName, style = MaterialTheme.typography.titleMedium, color = AlmostBlack)
                Text(threadDescription, style = MaterialTheme.typography.labelSmall, color = Color.LightGray)
            }
        }
    }
}