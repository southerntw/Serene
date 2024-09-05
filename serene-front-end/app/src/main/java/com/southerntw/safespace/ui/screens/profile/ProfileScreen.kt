package com.southerntw.safespace.ui.screens.profile

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.data.api.ProfileResponse
import com.southerntw.safespace.ui.composables.MoodBoard
import com.southerntw.safespace.ui.composables.MoodDisplay
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.util.UiState
import com.southerntw.safespace.viewmodel.ProfileViewModel

val dummyMood = intArrayOf(
    1, 2, 3, 1, 2, 3, 1,
    2, 3, 2, 1, 2, 3, 3,
    1, 2, 3, 3, 2, 1, 1
)

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val profileResponse by viewModel.profileResponse.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.tryUserId() // Ensure userId is fetched
        viewModel.getUser()   // Fetch the user profile
    }

    when (profileResponse) {
        is UiState.Loading -> {
            // Show ProfileContent with reduced opacity and disabled settings button
            ProfileContent(
                modifier = modifier.alpha(0.7f),
                userAvatar = R.drawable.mock_avatar,
                userName = "Loading...",
                userGender = "",
                userAge = "",
                userJoinYear = "",
                userAbout = "Loading...",
                userMood = "",
                onSettingsClicked = {}
            )
        }
        is UiState.Success -> {
            val profileData = (profileResponse as UiState.Success<ProfileResponse>).data?.profileData
            ProfileContent(
                userAvatar = R.drawable.mock_avatar, // Replace with profileData.avatar if it is a URL
                userName = profileData?.name ?: "Unknown",
                userGender = profileData?.gender ?: "Unset",
                userAge = profileData?.birthdate ?: "Unknown", // Convert birthdate to age if needed
                userJoinYear = "2024", // Adjust as necessary
                userAbout = profileData?.about ?: "No description available",
                userMood = "Excellent",
                onSettingsClicked = {
                    navHostController.navigate(Screen.Settings.route)
                }
            )
        }
        is UiState.Failure -> {
            // Show error UI
            Text("Failed to load profile")
        }
        else -> {
            Text("Failed to load profile")
        }
    }
}


@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    onSettingsClicked: () -> Unit,
    userAvatar: Int,
    userName: String,
    userGender: String,
    userAge: String,
    userJoinYear: String,
    userAbout: String,
    userMood: String
) {
    Box(modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier.height(34.dp))
            AsyncImage(model = userAvatar, contentDescription = "User Avatar", modifier = modifier
                .size(118.dp)
            )
            Spacer(modifier.height(16.dp))

            Text(userName, color = AlmostBlack, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold), modifier = modifier.padding(bottom = 8.dp))
            Text("$userGender, $userAge years old", color = AlmostBlack, style = MaterialTheme.typography.bodyMedium, modifier = modifier.padding(bottom = 4.dp))
            Text("Joined in $userJoinYear", color = AlmostBlack, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier.height(32.dp))

            Column(
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp)) {
                Text(
                    "Today's Mood",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = AlmostBlack,
                    modifier = modifier.padding(bottom = 8.dp)
                )
                MoodDisplay(userMood = "Excellent")

//                Spacer(modifier.height(24.dp))
                // TODO: Check in other devices.
//                Text(
//                    "Moods Within Last 21 Days",
//                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
//                    color = AlmostBlack
//                )
//                Box(modifier.fillMaxWidth()) {
//                    MoodBoard(moodLevel = dummyMood)
//                }

                Spacer(modifier.height(24.dp))
                Text(
                    "About you",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = AlmostBlack,
                    modifier = modifier.padding(bottom = 8.dp)
                )
                Text(
                    userAbout,
                    style = MaterialTheme.typography.bodyMedium,
                    color = AlmostBlack
                )
            }
        }
        IconButton(onClick = onSettingsClicked, modifier = modifier
            .align(Alignment.TopEnd)
            .padding(top = 12.dp)) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.icon_setting),
                contentDescription = "Setting Button",
                tint = AlmostBlack,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
