package com.southerntw.safespace.ui.screens.auth

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.southerntw.safespace.ui.composables.AuthTextField
import com.southerntw.safespace.ui.composables.ButtonFilled
import com.southerntw.safespace.ui.composables.ButtonOnboard
import com.southerntw.safespace.ui.composables.ButtonOutlined
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.MoodGreen

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    OnBoardingContent(onNextClicked = {
        navHostController.navigate(Screen.FillData.route)
    })
}

@Composable
fun OnBoardingContent(modifier: Modifier = Modifier, onNextClicked: () -> Unit) {
    Box(
        modifier
            .fillMaxSize()
            .background(MoodGreen)
    ) {
        Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "We welcome you to Safespace!", style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp
            ),
                modifier = modifier.align(Alignment.CenterHorizontally).padding(bottom = 8.dp)
            )

            Text(text = "Before using the app, we'd like you to fill some additional informations about yourself to make your experience better!", style = MaterialTheme.typography.bodyLarge,
                modifier = modifier.align(Alignment.CenterHorizontally).padding(bottom = 52.dp),
                textAlign = TextAlign.Center
            )
            ButtonOnboard(modifier = modifier, onClicked = onNextClicked, text = "Okay! Let's go.")
        }
    }
}
