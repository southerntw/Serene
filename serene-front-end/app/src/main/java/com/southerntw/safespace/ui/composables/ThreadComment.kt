package com.southerntw.safespace.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.White

@Composable
fun ThreadComment(modifier: Modifier = Modifier, commentName: String, commentDescription: String, commentContent: String) {
    Box(modifier.fillMaxWidth().background(White)) {
        Column(modifier.fillMaxSize().padding(vertical = 24.dp, horizontal = 16.dp)) {
            Text(commentName, style = MaterialTheme.typography.bodySmall, color = Color.LightGray, modifier = modifier.padding(bottom = 8.dp))
            Text(commentDescription, style = MaterialTheme.typography.bodySmall, color = Color.LightGray, modifier = modifier.padding(bottom = 16.dp))
            Text(commentContent, style = MaterialTheme.typography.titleSmall, color = AlmostBlack)
        }
    }
}