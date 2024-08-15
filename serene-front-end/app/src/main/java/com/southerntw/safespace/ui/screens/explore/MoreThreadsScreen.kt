package com.southerntw.safespace.ui.screens.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.southerntw.safespace.data.api.ThreadsData
import kotlinx.coroutines.flow.Flow

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
        }
    )
}

@Composable
fun MoreThreadsContent(
    threads: LazyPagingItems<ThreadsData>,
    modifier: Modifier = Modifier,
    onThreadClicked: (Int) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Text(
            "Threads",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = AlmostBlack,
            modifier = modifier.padding(top = 48.dp, start = 28.dp, end = 28.dp, bottom = 24.dp)
        )

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