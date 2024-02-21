package com.southerntw.safespace.ui.navigation.screen

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val screen: Screen
)
