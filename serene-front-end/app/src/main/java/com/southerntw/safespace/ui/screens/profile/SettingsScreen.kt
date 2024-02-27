package com.southerntw.safespace.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.SettingsClickable
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.MoodRed
import com.southerntw.safespace.viewmodel.ProfileViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val userExist by viewModel.userExist

    SideEffect {
        viewModel.tryUserExist()
    }
    LaunchedEffect(userExist) {
        if (!userExist) {
            navHostController.navigate(Screen.Start.route) {
                popUpTo(0)
            }
        }
    }

    Column(modifier.fillMaxSize()) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = AlmostBlack, modifier = modifier.padding(top = 48.dp, start = 28.dp, end = 28.dp, bottom = 24.dp))

        Column {
            SettingsClickable(icon = R.drawable.icon_edit, iconColor = AlmostBlack, settingsName = "Edit your profile") {
                navHostController.navigate(Screen.Edit.route)
            }
            SettingsClickable(icon = R.drawable.icon_logout, iconColor = MoodRed, settingsName = "Logout from your account") {
                viewModel.deleteSession()
            }
        }
    }
}