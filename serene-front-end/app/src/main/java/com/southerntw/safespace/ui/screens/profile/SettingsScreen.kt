package com.southerntw.safespace.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.SettingsClickable
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.MoodRed

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    Column(modifier.fillMaxSize()) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = AlmostBlack, modifier = modifier.padding(top = 48.dp, start = 28.dp, end = 28.dp, bottom = 24.dp))

        Column {
            SettingsClickable(icon = R.drawable.icon_edit, iconColor = AlmostBlack, settingsName = "Edit your profile") {
                navHostController.navigate(Screen.Edit.route)
            }
            SettingsClickable(icon = R.drawable.icon_logout, iconColor = MoodRed, settingsName = "Logout from your account") {
                navHostController.navigate(Screen.Start.route) {
                    popUpTo(Screen.Settings.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}