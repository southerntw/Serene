package com.southerntw.safespace.ui.screens.explore

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.GrayBackground
import com.southerntw.safespace.ui.theme.GrayOnBackground
import com.southerntw.safespace.ui.theme.White

@Composable
fun ThreadScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    ThreadContent(modifier = modifier, threadTitle = "I am not feeling great today.. Help!", threadDescription = "By somebody, 12 December 2023 at 3:30", threadContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum")
}

@Composable
fun ThreadContent(modifier: Modifier, threadTitle: String, threadDescription: String, threadContent: String) {
        Column(
            Modifier
                .fillMaxSize()
                .background(GrayBackground)
                .verticalScroll(rememberScrollState()))
        {
            Column(modifier.background(White).padding(top = 38.dp, start = 28.dp, end = 28.dp, bottom = 24.dp)) {
                Text(threadTitle, color = AlmostBlack, style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, lineHeight = 36.sp), modifier = modifier.padding(bottom = 8.dp))
                Text(threadDescription, color = Color.LightGray, style = MaterialTheme.typography.bodySmall, modifier = modifier.padding(bottom = 32.dp))

                Text(threadContent, color = AlmostBlack, style = MaterialTheme.typography.titleSmall)
            }
            Canvas(modifier = Modifier.size(1.dp, 32.dp).align(Alignment.CenterHorizontally)) {
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                drawLine(
                    color = GrayOnBackground,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 2f,
                    pathEffect = pathEffect
                )
            }
        }
}