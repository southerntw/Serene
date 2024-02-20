package com.southerntw.safespace.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.AuthTextField
import com.southerntw.safespace.ui.composables.ButtonFilled
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.White

@Composable
fun SignInScreen(modifier: Modifier = Modifier,
                navHostController: NavHostController
) {
    SignInContent(
        modifier = modifier,
        onSignInClicked = {},
        inputEmail = "",
        inputPassword = "",
        onEmailChanged = {},
        onPasswordChanged = {}
    )
}

@Composable
fun SignInContent(
    modifier: Modifier,
    onSignInClicked: () -> Unit,
    inputEmail: String,
    inputPassword: String,
    onPasswordChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
) {
    Column(modifier.fillMaxSize()) {
        AsyncImage(model = R.drawable.shape_login, contentDescription = "Shape", modifier = modifier.padding(bottom = 14.dp).fillMaxWidth())
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)) {
            Text(text = "Glad to have you back.", style = MaterialTheme.typography.headlineLarge, modifier = modifier.padding(bottom = 12.dp), color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Please enter your credentials.", style = MaterialTheme.typography.headlineSmall, modifier = modifier.padding(bottom = 32.dp), color = MaterialTheme.colorScheme.onBackground)

            AuthTextField(
                label = "Email",
                input = inputEmail,
                onInputChanged = onEmailChanged
            )

            AuthTextField(
                label = "Password",
                input = inputPassword,
                onInputChanged = onPasswordChanged,
                isPassword = true
            )

            ButtonFilled(modifier = modifier.fillMaxWidth(), onClicked = onSignInClicked, text = "Sign In")
        }
        Spacer(Modifier.weight(1F))
        AsyncImage(model = R.drawable.illust_login, contentDescription = "Illustration Login", modifier = modifier.padding(14.dp).fillMaxWidth())
    }
}