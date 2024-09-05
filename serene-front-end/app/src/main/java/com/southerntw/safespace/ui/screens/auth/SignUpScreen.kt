package com.southerntw.safespace.ui.screens.auth

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.AuthTextField
import com.southerntw.safespace.ui.composables.ButtonFilled
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.util.AuthUiState
import com.southerntw.safespace.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(modifier: Modifier = Modifier,
                 viewModel: AuthViewModel = hiltViewModel(),
                 navHostController: NavHostController
) {
    val name by viewModel.nameRegister
    val email by viewModel.emaiLRegister
    val password by viewModel.passwordRegister
    val confirmPassword by viewModel.confirmPasswordRegister

    val context = LocalContext.current

    viewModel.registerResponse.collectAsState(initial = AuthUiState.Idle).value.let { uiState ->
        when (uiState) {
            is AuthUiState.Idle -> {
                SignUpContent(
                    modifier = modifier,
                    onSignUpClicked = viewModel::register,
                    inputName = name,
                    inputEmail = email,
                    inputPassword = password,
                    inputConfirmPassword = confirmPassword,
                    onNameChanged = viewModel::changeNameRegister,
                    onEmailChanged = viewModel::changeEmailRegister,
                    onPasswordChanged = viewModel::changePasswordRegister,
                    onConfirmPasswordChanged = viewModel::changeConfirmPasswordRegister,
                )
            }
            is AuthUiState.Load -> {
                SignUpContent(
                    modifier = modifier.alpha(0.7F),
                    onSignUpClicked = viewModel::register,
                    inputName = "",
                    inputEmail = "",
                    inputPassword = "",
                    inputConfirmPassword = "",
                    onNameChanged = {},
                    onEmailChanged = {},
                    onPasswordChanged = {},
                    onConfirmPasswordChanged = {}
                )
            }
            is AuthUiState.Success -> {
                LaunchedEffect(key1 = true) {
                    Toast.makeText(context, "Register Successful", Toast.LENGTH_SHORT).show()
                    navHostController.navigate(Screen.Start.route) {
                        popUpTo(0)
                    }
                }
            }
            is AuthUiState.Failure -> {
                SignUpContent(
                    modifier = modifier,
                    onSignUpClicked = viewModel::register,
                    inputName = name,
                    inputEmail = email,
                    inputPassword = password,
                    inputConfirmPassword = confirmPassword,
                    onNameChanged = viewModel::changeNameRegister,
                    onEmailChanged = viewModel::changeEmailRegister,
                    onPasswordChanged = viewModel::changePasswordRegister,
                    onConfirmPasswordChanged = viewModel::changeConfirmPasswordRegister,
                )
                LaunchedEffect(key1 = true) {
                    Toast.makeText(context, "Register Gagal", Toast.LENGTH_SHORT).show()
                }
            }

            else -> {}
        }
    }
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
                onInputChanged = onPasswordChanged,
                isPassword = true
            )
            AuthTextField(
                label = "Confirm Password",
                input = inputConfirmPassword,
                onInputChanged = onConfirmPasswordChanged,
                isPassword = true
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