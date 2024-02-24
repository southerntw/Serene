package com.southerntw.safespace.ui.screens.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.southerntw.safespace.ui.composables.ThreadEntry
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack

@Composable
fun MoreThreadsScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    MoreThreadsContent(onThreadClicked = { navHostController.navigate(Screen.Thread.route) })
}

@Composable
fun MoreThreadsContent(modifier: Modifier = Modifier, onThreadClicked: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Text("Threads", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = AlmostBlack, modifier = modifier.padding(top = 48.dp, start = 28.dp, end = 28.dp, bottom = 24.dp))

        LazyColumn(modifier.fillMaxWidth().padding(start = 28.dp, end = 28.dp)) {
            items(15) { _ ->
                ThreadEntry(
                    threadName = "I have to discuss something",
                    threadDescription = "by southerntw, 12 December 2023 at 3:30",
                    onClick = onThreadClicked
                )
            }
        }
    }
}