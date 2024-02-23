package com.southerntw.safespace.ui.screens.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.HomeEntry
import com.southerntw.safespace.ui.composables.NewsEntry
import com.southerntw.safespace.ui.composables.TagEntry
import com.southerntw.safespace.ui.composables.ThreadEntry
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack

@Composable
fun ExploreScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    ExploreContent(modifier = modifier, onTagClicked = {},
        onThreadClicked = {
            navHostController.navigate(Screen.Thread.route)
        },
        onNewsClicked = {},
        onMoreNewsClicked = {},
        onMoreThreadClicked = {})
}

@Composable
fun ExploreContent(modifier: Modifier, onTagClicked: () -> Unit, onThreadClicked: () -> Unit, onNewsClicked: () -> Unit, onMoreNewsClicked: () -> Unit, onMoreThreadClicked: () -> Unit) {
    Column(Modifier.fillMaxSize()) {

    Text("Explore", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = AlmostBlack, modifier = modifier.padding(top = 48.dp, start = 28.dp, end = 28.dp, bottom = 24.dp))
    Column(
        modifier
            .padding(start = 28.dp, end = 28.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Column(
            modifier = modifier.padding( bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Sort by Tag",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = AlmostBlack
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(5) { index ->
                    TagEntry(tagName = "#AskForHelp", onClick = onTagClicked)
                }
            }
        }

        Column(
            modifier = modifier.padding(bottom = 48.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Recent Threads",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = AlmostBlack
                )
                Spacer(modifier = modifier.weight(1F))
                Text(
                    "See more >>",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = modifier.clickable { onMoreThreadClicked })
            }
            Column {
                ThreadEntry(
                    threadName = "I have to discuss something",
                    threadDescription = "by southerntw, 12 December 2023 at 3:30",
                    onClick = onThreadClicked
                )
                ThreadEntry(
                    threadName = "I have to discuss something",
                    threadDescription = "by southerntw, 12 December 2023 at 3:30",
                    onClick = onThreadClicked
                )
                ThreadEntry(
                    threadName = "I have to discuss something",
                    threadDescription = "by southerntw, 12 December 2023 at 3:30",
                    onClick = onThreadClicked
                )
            }
        }

        Column(
            modifier = modifier.padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Recent News",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = AlmostBlack
                )
                Spacer(modifier = modifier.weight(1F))
                Text(
                    "See more >>",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = modifier.clickable { onMoreNewsClicked() })
            }
            Column {
                NewsEntry(
                    newsName = "New Way of dealing with Mental Health has been Found!!!",
                    newsDescription = "12 December 2023 at 3:30",
                    newsImage = R.drawable.mock_newsimage,
                    onClick = onNewsClicked
                )
                NewsEntry(
                    newsName = "I have to discuss something",
                    newsDescription = "12 December 2023 at 3:30",
                    newsImage = R.drawable.mock_newsimage,
                    onClick = onNewsClicked
                )
                NewsEntry(
                    newsName = "I have to discuss something",
                    newsDescription = "12 December 2023 at 3:30",
                    newsImage = R.drawable.mock_newsimage,
                    onClick = onNewsClicked
                )
            }
        }
    }
    }
}