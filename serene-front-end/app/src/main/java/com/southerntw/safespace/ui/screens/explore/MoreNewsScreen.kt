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
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.NewsEntry
import com.southerntw.safespace.ui.composables.ThreadEntry
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack

@Composable
fun MoreNewsScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    MoreNewsContent(onNewsClicked = { navHostController.navigate(Screen.Thread.route) })
}

@Composable
fun MoreNewsContent(modifier: Modifier = Modifier, onNewsClicked: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Text("News", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = AlmostBlack, modifier = modifier.padding(top = 48.dp, start = 28.dp, end = 28.dp, bottom = 24.dp))

        LazyColumn(modifier.fillMaxWidth().padding(start = 28.dp, end = 28.dp)) {
            items(15) { _ ->
                NewsEntry(
                    newsName = "New Way of dealing with Mental Health has been Found!!!",
                    newsDescription = "12 December 2023 at 3:30",
                    newsImage = R.drawable.mock_newsimage,
                    onClick = onNewsClicked
                )
            }
        }
    }
}