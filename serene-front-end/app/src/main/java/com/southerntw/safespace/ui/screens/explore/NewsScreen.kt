package com.southerntw.safespace.ui.screens.explore

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.ThreadComment
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.GrayBackground
import com.southerntw.safespace.ui.theme.GrayOnBackground
import com.southerntw.safespace.ui.theme.White
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.southerntw.safespace.util.UiState
import com.southerntw.safespace.viewmodel.ExploreViewModel

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    newsId: Int,
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val newsResponse by viewModel.newsResponse.collectAsState()

    LaunchedEffect(newsId) {
        viewModel.getDetailedNews(newsId)
    }

    when (val response = newsResponse) {
        is UiState.Loading -> {
            // Show loading indicator
            Text("Loading...")
        }
        is UiState.Success -> {
            val news = response.data?.newsData
            news?.let {
                NewsContent(
                    modifier = modifier,
                    newsTitle = it.title ?: "No Title",
                    newsDescription = "${it.writer}, ${it.category}",
                    newsContent = it.content ?: "No Content",
                    newsHeader = it.thumbnail ?: ""
                )
            }
        }
        is UiState.Failure -> {
            // Show error message
            Text("Failed to load news")
        }
    }
}


@Composable
fun NewsContent(
    modifier: Modifier,
    newsTitle: String,
    newsDescription: String,
    newsContent: String,
    newsHeader: String
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = newsHeader,
            contentDescription = "News Header",
            modifier = modifier
                .fillMaxWidth()
                .height(225.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier
                .background(White)
                .padding(top = 38.dp, start = 28.dp, end = 28.dp, bottom = 24.dp)
        ) {
            Text(
                newsTitle,
                color = AlmostBlack,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, lineHeight = 36.sp),
                modifier = modifier.padding(bottom = 8.dp)
            )
            Text(
                newsDescription,
                color = Color.LightGray,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier.padding(bottom = 32.dp)
            )

            Text(
                newsContent,
                color = AlmostBlack,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}
