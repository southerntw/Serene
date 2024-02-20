package com.southerntw.safespace.ui.screens.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.AuthTextField
import com.southerntw.safespace.ui.composables.ButtonFilled

@Composable
fun SignUpScreen(modifier: Modifier = Modifier,
                 navHostController: NavHostController
) {
    SignUpContent(
        modifier = modifier,
        onSignUpClicked = {},
        inputName = "",
        inputEmail = "",
        inputPassword = "",
        inputConfirmPassword = "",
        onNameChanged = {},
        onEmailChanged = {},
        onPasswordChanged = {},
        onConfirmPasswordChanged = {},
    )
}

@Composable
fun SignUpContent(
    modifier: Modifier,
    onSignUpClicked: () -> Unit,
    inputName: String,
    inputEmail: String,
    inputPassword: String,
    inputConfirmPassword: String,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
) {
    Column(modifier.fillMaxSize()) {
        AsyncImage(model = R.drawable.shape_login, contentDescription = "Shape", modifier = modifier.padding(bottom = 14.dp).fillMaxWidth())
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)) {
            Text(text = "We're excited to have you!", style = MaterialTheme.typography.headlineLarge, modifier = modifier.padding(bottom = 12.dp), color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Please enter your credentials.", style = MaterialTheme.typography.headlineSmall, modifier = modifier.padding(bottom = 32.dp), color = MaterialTheme.colorScheme.onBackground)

            AuthTextField(
                label = "Name",
                input = inputName,
                onInputChanged = onNameChanged
            )
            AuthTextField(
                label = "Email",
                input = inputEmail,
                onInputChanged = onEmailChanged
            )
            AuthTextField(
                label = "Password",
                input = inputPassword,
                onInputChanged = onPasswordChanged
            )
            AuthTextField(
                label = "Confirm Password",
                input = inputConfirmPassword,
                onInputChanged = onConfirmPasswordChanged
            )
            ButtonFilled(modifier = modifier.fillMaxWidth(), onClicked = onSignUpClicked, text = "Sign Up")
        }
        Spacer(Modifier.weight(1F))
        AsyncImage(model = R.drawable.logo, contentDescription = "Safespace Logo", modifier = modifier
            .height(36.dp)
            .width(36.dp)
            .padding(bottom = 12.dp)
            .align(Alignment.CenterHorizontally)
        )
    }
}