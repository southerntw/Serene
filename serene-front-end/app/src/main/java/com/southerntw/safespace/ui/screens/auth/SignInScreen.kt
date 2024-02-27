package com.southerntw.safespace.ui.screens.auth

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.AuthTextField
import com.southerntw.safespace.ui.composables.ButtonFilled
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.White
import com.southerntw.safespace.util.AuthUiState
import com.southerntw.safespace.viewmodel.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val email by viewModel.emaiLLogin
    val password by viewModel.passwordLogin
    val userExist by viewModel.userExist

    val context = LocalContext.current

    SideEffect {
        viewModel.tryUserExist()
    }
    LaunchedEffect(userExist) {
        if (userExist) {
            navHostController.navigate(Screen.Home.route) {
                popUpTo(0)
            }

        }
    }

    viewModel.loginResponse.collectAsState(initial = AuthUiState.Idle).value.let { uiState ->
        when (uiState) {
            is AuthUiState.Idle -> {
                SignInContent(
                    modifier = modifier,
                    onSignInClicked = viewModel::login,
                    inputEmail = email,
                    inputPassword = password,
                    onEmailChanged = viewModel::changeEmailLogin,
                    onPasswordChanged = viewModel::changePasswordLogin
                )
            }
            is AuthUiState.Load -> {
                SignInContent(
                    modifier = modifier.alpha(0.7F),
                    onSignInClicked = { },
                    inputEmail = email,
                    inputPassword = password,
                    onEmailChanged = {},
                    onPasswordChanged = {}
                )
            }
            is AuthUiState.Success -> {
                SignInContent(
                    modifier = modifier,
                    onSignInClicked = viewModel::login,
                    inputEmail = email,
                    inputPassword = password,
                    onEmailChanged = viewModel::changeEmailLogin,
                    onPasswordChanged = viewModel::changePasswordLogin
                )
                LaunchedEffect(key1 = true) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                }
            }
            is AuthUiState.Failure -> {
                SignInContent(
                    modifier = modifier,
                    onSignInClicked = viewModel::login,
                    inputEmail = email,
                    inputPassword = password,
                    onEmailChanged = viewModel::changeEmailLogin,
                    onPasswordChanged = viewModel::changePasswordLogin
                )
                LaunchedEffect(key1 = true) {
                    Toast.makeText(context, "Login Gagal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
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
        AsyncImage(model = R.drawable.shape_login, contentDescription = "Shape", modifier = modifier
            .padding(bottom = 14.dp)
            .fillMaxWidth())
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
        AsyncImage(model = R.drawable.illust_login, contentDescription = "Illustration Login", modifier = modifier
            .padding(14.dp)
            .fillMaxWidth())
    }
}