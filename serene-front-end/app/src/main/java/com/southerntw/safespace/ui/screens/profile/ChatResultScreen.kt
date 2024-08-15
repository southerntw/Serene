package com.southerntw.safespace.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.data.api.BotEncourageResponses
import com.southerntw.safespace.ui.composables.ButtonFilled
import com.southerntw.safespace.ui.composables.NewsRecommendation
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.White
import com.southerntw.safespace.util.ChatUiState
import com.southerntw.safespace.viewmodel.EncourageViewModel

@Composable
fun ChatResultScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val viewModel: EncourageViewModel = hiltViewModel()
    val encourageResponses = viewModel.encourageResponses.collectAsState().value
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.tryUserToken()
        viewModel.tryUserId()
        viewModel.getEncouragement()
    }

    ChatResultContent(
        encourageResponses = encourageResponses,
        onConfirmClicked = {
            navHostController.navigate(Screen.Home.route) {
                popUpTo(Screen.ChatResult.route) {
                    inclusive = true
                }
            }
        }
    )
}

@Composable
fun ChatResultContent(
    encourageResponses: ChatUiState<BotEncourageResponses>,
    onConfirmClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(White)
                .padding(top = 8.dp, bottom = 32.dp)
        ) {
            AsyncImage(model = R.drawable.logo, contentDescription = "logo", modifier = modifier
                .align(Alignment.Center)
                .size(24.dp))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Thank you\nFor Sharing With Us.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp
                ),
                color = AlmostBlack,
                modifier = modifier.padding(bottom = 24.dp)
            )

            when (encourageResponses) {
                is ChatUiState.Load -> {
                    Text("Loading...", style = MaterialTheme.typography.bodyLarge, color = AlmostBlack,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp), textAlign = TextAlign.Center)
                }
                is ChatUiState.Success -> {
                    encourageResponses.data?.data?.let { data ->
                        Text(
                            data.message.toString(), style = MaterialTheme.typography.bodyLarge, color = AlmostBlack,
                            modifier = modifier
                                .width(300.dp)
                                .padding(bottom = 32.dp), textAlign = TextAlign.Center)

                        Spacer(modifier = modifier.weight(1F))

                        Text(
                            "These resources might help you...",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            color = AlmostBlack,
                            modifier = modifier
                                .width(352.dp)
                                .padding(bottom = 12.dp),
                            textAlign = TextAlign.Center
                        )

                        LazyRow(modifier = modifier.padding(bottom = 24.dp)) {
                            data.recommendations?.size?.let {
                                items(it) { index ->
                                    val recommendation = data.recommendations!![index]
                                    NewsRecommendation(
                                        newsHeader = recommendation.thumbnail,
                                        newsTitle = recommendation.title
                                    )
                                }
                            }
                        }
                    }
                }
                is ChatUiState.Failure -> {
                    Text("Failed to load data.", style = MaterialTheme.typography.bodyLarge, color = AlmostBlack,
                        modifier = modifier
                            .width(300.dp)
                            .padding(bottom = 32.dp), textAlign = TextAlign.Center)
                }
                is ChatUiState.Idle -> Unit
            }

            ButtonFilled(modifier = modifier.align(Alignment.CenterHorizontally), onClicked = onConfirmClicked, text = "Thanks for talking with me.")
            Spacer(modifier = modifier.height(32.dp))
        }
    }
}
