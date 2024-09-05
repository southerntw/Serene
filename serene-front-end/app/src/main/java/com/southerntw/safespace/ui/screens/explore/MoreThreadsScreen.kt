package com.southerntw.safespace.ui.screens.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.LazyPagingItems
import com.southerntw.safespace.ui.composables.ThreadEntry
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.viewmodel.ExploreViewModel
import androidx.paging.PagingData
import androidx.paging.compose.items
import com.southerntw.safespace.ui.theme.White
import kotlinx.coroutines.flow.Flow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.southerntw.safespace.data.api.ThreadsDetail


@Composable
fun MoreThreadsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val threads = viewModel.threads.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.fetchThreads()
    }

    MoreThreadsContent(
        threads = threads,
        onThreadClicked = { threadId ->
            navHostController.navigate(Screen.Thread.createRoute(threadId))
        },
        onAddThreadClicked = {
            navHostController.navigate(Screen.PostThread.route)
        }
    )
}

@Composable
fun MoreThreadsContent(
    threads: LazyPagingItems<ThreadsDetail>,
    modifier: Modifier = Modifier,
    onThreadClicked: (Int) -> Unit,
    onAddThreadClicked: () -> Unit // Added callback for Add Thread button
) {
    Column(Modifier.fillMaxSize()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 28.dp, end = 28.dp, bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically // Align items in the center vertically
        ) {
            Text(
                "Threads",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = AlmostBlack,
                modifier = Modifier.weight(1f) // Let the Text take as much space as possible
            )
            Spacer(modifier = Modifier.width(16.dp)) // Optional spacer for spacing between Text and Button
            Text(
                "+ Add Thread",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = modifier.clickable { onAddThreadClicked() })
        }

        LazyColumn(
            modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        ) {
            items(threads) { thread ->
                if (thread != null) {
                    ThreadEntry(
                        threadName = thread.title ?: "No Title",
                        threadDescription = "by ${thread.threadStarter}",
                        onClick = { onThreadClicked(thread.id ?: 0) }
                    )
                }
            }
        }
    }
}