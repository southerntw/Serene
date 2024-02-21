package com.southerntw.safespace.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.AuthTextField
import com.southerntw.safespace.ui.composables.ButtonFilled
import com.southerntw.safespace.ui.composables.ButtonOutlined
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    ProfileEditContent(modifier = Modifier,
        userAvatar = R.drawable.mock_avatar,
        userName = "Salazar Matilda",
        about = "Lorem Ipsum",
        birthdate = "12/12/2001",
        gender = "Female",
        onAboutChanged = {},
        onGenderChanged = {},
        onBirthdateChanged = {},
        onSaveClicked = {
                // TODO: Add Data saved Dialog
            },
        onSkipClicked = {
            navHostController.navigate(Screen.Home.route) {
            popUpTo(Screen.Home.route) {
                inclusive = true
            }
        }}
    )
}

@Composable
fun ProfileEditContent(modifier: Modifier,
                       userAvatar: Int,
                       userName: String,
                       about: String,
                       birthdate: String,
                       gender: String,
                       onAboutChanged: (String) -> Unit,
                       onBirthdateChanged: (String) -> Unit,
                       onGenderChanged: (String) -> Unit,
                       onSaveClicked: () -> Unit,
                       onSkipClicked: () -> Unit,
) {
    Column(modifier= modifier.fillMaxSize().padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier.padding(top = 34.dp))

        AsyncImage(model = userAvatar, contentDescription = "User Avatar", modifier = modifier
            .size(120.dp)
        )

        Spacer(modifier.padding(bottom = 24.dp))

        Text(userName, style = MaterialTheme.typography.headlineMedium, color = AlmostBlack, modifier = modifier.padding(bottom = 32.dp))

        AuthTextField(
            label = "About you",
            input = about,
            onInputChanged = onAboutChanged,
            multipleLines = true
        )

        AuthTextField(
            label = "Birthdate",
            input = birthdate,
            onInputChanged = onBirthdateChanged
        )

        AuthTextField(
            label = "Gender",
            input = gender,
            onInputChanged = onGenderChanged
        )

        ButtonFilled(modifier = modifier.fillMaxWidth(), onClicked = onSaveClicked, text = "Save Data")
        ButtonOutlined(modifier = modifier.fillMaxWidth(), onClicked = onSkipClicked, text = "Skip For Now")
    }
}