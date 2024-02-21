package com.southerntw.safespace.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.southerntw.safespace.ui.theme.AlmostBlack

@Composable
fun NewsEntry(modifier: Modifier = Modifier, newsName: String, newsDescription: String, newsImage: Int, onClick: () -> Unit) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .drawBehind {
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
            )
        })
    {
        Box(contentAlignment = Alignment.CenterStart, modifier = modifier.fillMaxSize()) {
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp), modifier = modifier.padding(top = 16.dp, bottom = 16.dp).fillMaxSize()) {
                AsyncImage(model = newsImage, contentDescription = "News Image", modifier = modifier.width(71.dp).height(79.dp).clip(
                    RoundedCornerShape(8.dp)
                ), contentScale = ContentScale.Crop)
                Column(verticalArrangement = Arrangement.Center, modifier = modifier.padding(vertical = 2.dp).height(79.dp)) {
                    Text(newsName, style = MaterialTheme.typography.titleMedium, color = AlmostBlack)
                    Spacer(modifier = modifier.weight(1F))
                    Text(newsDescription, style = MaterialTheme.typography.labelSmall, color = Color.LightGray)
                }
            }
        }
    }
}