package com.southerntw.safespace.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.Gray02

@Composable
fun TagEntry(modifier: Modifier = Modifier, tagName: String, onClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = modifier
        .width(95.dp)
        .height(110.dp)
        .clickable { onClick() }) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(model = R.drawable.tag_bird, contentDescription = "Bird")
            Text(tagName, color = AlmostBlack, style = MaterialTheme.typography.labelSmall)
        }
    }
}