package com.southerntw.safespace.ui.screens.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.NewsEntry
import com.southerntw.safespace.ui.composables.TagEntry
import com.southerntw.safespace.ui.composables.ThreadEntry
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.viewmodel.ExploreViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.PagingData
import com.southerntw.safespace.data.api.NewsData
import com.southerntw.safespace.data.api.ThreadsDetail
import kotlinx.coroutines.flow.Flow

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val threadsFlow: Flow<PagingData<ThreadsDetail>> = viewModel.threads
    val threads = threadsFlow.collectAsLazyPagingItems()

    val newsFlow: Flow<PagingData<NewsData>> = viewModel.news
    val news = newsFlow.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.fetchThreads()
        viewModel.fetchNews()
    }

    ExploreContent(
        modifier = modifier,
        threads = threads,
        news = news,
        onTagClicked = {},
        onThreadClicked = { threadId ->
            navHostController.navigate(Screen.Thread.createRoute(threadId))
        },
        onNewsClicked = { newsId ->
            navHostController.navigate(Screen.News.createRoute(newsId))
        },
        onMoreNewsClicked = {
            navHostController.navigate(Screen.MoreNews.route)
        },
        onMoreThreadClicked = {
            navHostController.navigate(Screen.MoreThreads.route)
        }
    )
}

@Composable
fun ExploreContent(
    modifier: Modifier,
    threads: LazyPagingItems<ThreadsDetail>,
    news: LazyPagingItems<NewsData>,
    onTagClicked: () -> Unit,
    onThreadClicked: (Int) -> Unit,
    onNewsClicked: (Int) -> Unit,
    onMoreNewsClicked: () -> Unit,
    onMoreThreadClicked: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Text(
            "Explore",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = AlmostBlack,
            modifier = modifier.padding(top = 48.dp, start = 28.dp, end = 28.dp, bottom = 24.dp)
        )
        Column(
            modifier = modifier
                .padding(start = 28.dp, end = 28.dp)
                .verticalScroll(rememberScrollState())
        ) {
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
                        modifier = modifier.clickable { onMoreThreadClicked() })
                }
                Column {
                    // Use indices to limit the number of items displayed
                    for (index in 0 until minOf(3, threads.itemCount)) {
                        val thread = threads[index]
                        if (thread != null) {
                            ThreadEntry(
                                threadName = thread.text ?: "No Title",
                                threadDescription = "by ${thread.threadStarter}, ${thread.id}",
                                onClick = { onThreadClicked(thread.id ?: 0) }
                            )
                        }
                    }
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
                    // Use indices to limit the number of items displayed
                    for (index in 0 until minOf(3, news.itemCount)) {
                        val newsItem = news[index]
                        if (newsItem != null) {
                            NewsEntry(
                                newsName = newsItem.title ?: "No Title",
                                newsDescription = newsItem.category ?: "",
                                newsImage = newsItem.thumbnail ?: "",
                                onClick = { onNewsClicked(newsItem.id ?: 0) }
                            )
                        }
                    }
                }
            }
        }
    }
}