package com.southerntw.safespace.ui.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.southerntw.safespace.ui.theme.AlmostBlack

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    Box(modifier.fillMaxSize()) {
        Text(text = "This is profile.", color = AlmostBlack)
    }
}