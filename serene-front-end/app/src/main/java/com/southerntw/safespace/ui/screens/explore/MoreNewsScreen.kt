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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.southerntw.safespace.R
import com.southerntw.safespace.data.api.NewsData
import com.southerntw.safespace.ui.composables.NewsEntry
import com.southerntw.safespace.ui.composables.ThreadEntry
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.viewmodel.ExploreViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.flow.Flow

@Composable
fun MoreNewsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val newsFlow: Flow<PagingData<NewsData>> = viewModel.news
    val news = newsFlow.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }

    MoreNewsContent(
        news = news,
        onNewsClicked = { newsId ->
            navHostController.navigate(Screen.News.createRoute(newsId))
        }
    )
}

@Composable
fun MoreNewsContent(
    news: LazyPagingItems<NewsData>,
    modifier: Modifier = Modifier,
    onNewsClicked: (Int) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Text(
            "News",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = AlmostBlack,
            modifier = modifier.padding(top = 48.dp, start = 28.dp, end = 28.dp, bottom = 24.dp)
        )

        LazyColumn(
            modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        ) {
            items(news) { newsItem ->
                if (newsItem != null) {
                    NewsEntry(
                        newsName = newsItem.title ?: "No Title",
                        newsDescription = newsItem.category ?: "",
                        newsImage = newsItem.thumbnail ?: "",
                        onClick = { newsItem.id?.let { onNewsClicked(it) } }
                    )
                }
            }
        }
    }
}
