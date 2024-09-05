package com.southerntw.safespace.ui.screens.profile

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.data.api.ProfileResponse
import com.southerntw.safespace.ui.composables.AuthTextField
import com.southerntw.safespace.ui.composables.ButtonFilled
import com.southerntw.safespace.ui.composables.ButtonOutlined
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.util.AuthUiState
import com.southerntw.safespace.util.UiState
import com.southerntw.safespace.viewmodel.ProfileViewModel

@Composable
fun SettingsEditScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.tryUserId()
    }

    // Manage editing state
    var userNameEdit by remember { mutableStateOf("") }
    var userAboutEdit by remember { mutableStateOf("") }
    var userBirthDateEdit by remember { mutableStateOf("") }
    var userGenderEdit by remember { mutableStateOf("") }

    viewModel.editResponse.collectAsState(initial = AuthUiState.Idle).value.let { uiState ->
        // Handle different UI states
        when (uiState) {
            is AuthUiState.Idle -> {
                SettingsEditContent(
                    modifier = modifier,
                    userAvatar = R.drawable.mock_avatar, // Assuming static avatar for now
                    userName = userNameEdit,
                    userNameEdit = userNameEdit,
                    about = userAboutEdit,
                    birthdate = userBirthDateEdit,
                    gender = userGenderEdit,
                    onUsernameEditChanged = { newName -> userNameEdit = newName },
                    onAboutChanged = { newAbout -> userAboutEdit = newAbout },
                    onBirthdateChanged = { newBirthDate -> userBirthDateEdit = newBirthDate },
                    onGenderChanged = { newGender -> userGenderEdit = newGender },
                    onSaveClicked = {
                        viewModel.updateProfileData(
                            name = userNameEdit,
                            about = userAboutEdit,
                            birthDate = userBirthDateEdit,
                            gender = userGenderEdit
                        )
                        viewModel.saveProfile()
                        navHostController.popBackStack()
                    }
                )
            }

            is AuthUiState.Load -> {
                SettingsEditContent(
                    modifier = modifier.alpha(0.7F),
                    userAvatar = R.drawable.mock_avatar, // Assuming static avatar for now
                    userName = userNameEdit,
                    userNameEdit = userNameEdit,
                    about = userAboutEdit,
                    birthdate = userBirthDateEdit,
                    gender = userGenderEdit,
                    onUsernameEditChanged = { },
                    onAboutChanged = { },
                    onBirthdateChanged = { },
                    onGenderChanged = { },
                    onSaveClicked = {}
                )
            }

            is AuthUiState.Success -> {
                LaunchedEffect(Unit) {
                    navHostController.popBackStack()
                }
                LaunchedEffect(key1 = true) {
                    Toast.makeText(context, "Edit Successful", Toast.LENGTH_SHORT).show()
                }
            }

            is AuthUiState.Failure -> {
                LaunchedEffect(key1 = true) {
                    Toast.makeText(context, "Error occured. Please try again.", Toast.LENGTH_SHORT).show()
                }
                // Handle error state
                SettingsEditContent(
                    modifier = modifier,
                    userAvatar = R.drawable.mock_avatar, // Assuming static avatar for now
                    userName = userNameEdit,
                    userNameEdit = userNameEdit,
                    about = userAboutEdit,
                    birthdate = userBirthDateEdit,
                    gender = userGenderEdit,
                    onUsernameEditChanged = { newName -> userNameEdit = newName },
                    onAboutChanged = { newAbout -> userAboutEdit = newAbout },
                    onBirthdateChanged = { newBirthDate -> userBirthDateEdit = newBirthDate },
                    onGenderChanged = { newGender -> userGenderEdit = newGender },
                    onSaveClicked = {
                        viewModel.updateProfileData(
                            name = userNameEdit,
                            about = userAboutEdit,
                            birthDate = userBirthDateEdit,
                            gender = userGenderEdit
                        )
                        viewModel.saveProfile()
                        navHostController.popBackStack()
                    }
                )
            }

            else -> {}
        }
    }
}


@Composable
fun SettingsEditContent(
    modifier: Modifier,
    userAvatar: Int,
    userName: String,
    userNameEdit: String,
    about: String,
    birthdate: String,
    gender: String,
    onUsernameEditChanged: (String) -> Unit,
    onAboutChanged: (String) -> Unit,
    onBirthdateChanged: (String) -> Unit,
    onGenderChanged: (String) -> Unit,
    onSaveClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.padding(top = 34.dp))

        AsyncImage(
            model = userAvatar,
            contentDescription = "User Avatar",
            modifier = modifier.size(120.dp)
        )

        Spacer(modifier = modifier.padding(bottom = 24.dp))

        Text(
            userName,
            style = MaterialTheme.typography.headlineMedium,
            color = AlmostBlack,
            modifier = modifier.padding(bottom = 32.dp)
        )

        AuthTextField(
            label = "Your name",
            input = userNameEdit,
            onInputChanged = onUsernameEditChanged
        )

        AuthTextField(
            label = "About you",
            input = about,
            onInputChanged = onAboutChanged,
            multipleLines = true
        )

        AuthTextField(
            label = "Birthdate (format: YYYY-MM-DD)",
            input = birthdate,
            onInputChanged = onBirthdateChanged
        )

        AuthTextField(
            label = "Gender (format: Male/Female)",
            input = gender,
            onInputChanged = onGenderChanged
        )

        ButtonFilled(
            modifier = modifier.fillMaxWidth(),
            onClicked = onSaveClicked,
            text = "Save Data"
        )
    }
}