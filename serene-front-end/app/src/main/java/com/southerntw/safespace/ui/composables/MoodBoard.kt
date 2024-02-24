package com.southerntw.safespace.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.southerntw.safespace.ui.theme.MoodGreen
import com.southerntw.safespace.ui.theme.MoodRed
import java.util.Date

@Composable
fun MoodBoard(modifier: Modifier = Modifier, moodLevel: IntArray) {
    Canvas(modifier = modifier.fillMaxWidth().padding(50.dp)) {
        val squareSize = Size(50f, 50f)
        val gapSize = 5f
        val numRows = (moodLevel.size + 6) / 7
        val canvasWidth = squareSize.width * 7 + gapSize * 6
        val canvasHeight = squareSize.height * numRows + gapSize * (numRows - 1)
        val startX = (size.width - canvasWidth) / 2
        val startY = (size.height - canvasHeight) / 2

        for (i in 0 until moodLevel.size) {
            val row = i / 7
            val col = i % 7
            val x = startX + col * (squareSize.width + gapSize)
            val y = startY + row * (squareSize.height + gapSize)
            drawMoodSquare(moodLevel[i], Offset(x, y))
        }
    }
}

internal fun DrawScope.drawMoodSquare(level: Int, offset: Offset) {
    val color = when (level) {
        1 -> MoodGreen
        2 -> Color.LightGray
        3 -> MoodRed
        else -> Color.White
    }

    drawRoundRect(
        color = color,
        topLeft = offset,
        size = Size(50f, 50f),
        cornerRadius = CornerRadius(5f, 5f)
    )
}