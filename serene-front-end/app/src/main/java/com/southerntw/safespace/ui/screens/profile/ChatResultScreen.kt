package com.southerntw.safespace.ui.screens.profile

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.ButtonFilled
import com.southerntw.safespace.ui.composables.NewsRecommendation
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.White

@Composable
fun ChatResultScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    ChatResultContent(encourageHeadline = "Thank you\nFor Sharing With Us.", onConfirmClicked = {
        navHostController.navigate(Screen.Home.route) {
            popUpTo(Screen.ChatResult.route) {
                inclusive = true
            }
        }
    }
    )
}

@Composable
fun ChatResultContent(modifier: Modifier = Modifier, encourageHeadline: String, onConfirmClicked: () -> Unit) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(White)
                .padding(top = 8.dp, bottom = 32.dp)) {
            AsyncImage(model = R.drawable.logo, contentDescription = "logo", modifier = modifier
                .align(Alignment.Center)
                .size(24.dp))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(encourageHeadline, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp
            ), color = AlmostBlack, modifier = modifier.padding(bottom = 24.dp))

            // TODO: NOTE! Limit this particular message to only 70 words or 444 characters.
            Text("\n" +
                    "In our chat about mental health, it seems like you're struggling with feelings of anxiety, like worrying a lot and feeling tense, and also experiencing periods of low mood and disinterest, which might indicate depression. Remember, I'm here for you as a friend, but it's important to talk to a professional for a proper diagnosis and support. Don't hesitate to reach out for help, you're not alone in this journey.", style = MaterialTheme.typography.bodyLarge, color = AlmostBlack,
                modifier = modifier
                    .width(300.dp)
                    .padding(bottom = 32.dp), textAlign = TextAlign.Center)

            Spacer(modifier = modifier.weight(1F))

            Text("These resources might help you...", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = AlmostBlack,
                modifier = modifier
                    .width(352.dp)
                    .padding(bottom = 12.dp), textAlign = TextAlign.Center)

            LazyRow(modifier = modifier.padding(bottom = 24.dp)) {
                items(15) {
                    NewsRecommendation(newsHeader = R.drawable.mock_newsimage, newsTitle = "This will shock you a lot. But it is what it is")
                }
            }
            ButtonFilled(modifier = modifier.align(Alignment.CenterHorizontally), onClicked = onConfirmClicked, text = "Thanks for talking with me.")
            Spacer(modifier = modifier.height(32.dp))
        }
    }
}