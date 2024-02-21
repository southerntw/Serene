package com.southerntw.safespace.ui.composables

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.navigation.screen.NavigationItem
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.Gray02
import com.southerntw.safespace.ui.theme.GrayText
import com.southerntw.safespace.ui.theme.Lime01
import com.southerntw.safespace.ui.theme.Lime02
import com.southerntw.safespace.ui.theme.White

@Composable
fun BottomBar(modifier: Modifier = Modifier, navHostController: NavHostController, name: String) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var selectedItem by remember { mutableIntStateOf(0) }
    val navigationItem = listOf(
        NavigationItem(
            label = "Home",
            icon = ImageVector.vectorResource(R.drawable.icon_home),
            screen = Screen.Home
        ),
        NavigationItem(
            label = "Explore",
            icon = ImageVector.vectorResource(R.drawable.icon_community),
            screen = Screen.Explore
        ),
        NavigationItem(
            label = name,
            icon = ImageVector.vectorResource(R.drawable.icon_profile),
            screen = Screen.Profile
        )
    )

    NavigationBar(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.secondary) {
        navigationItem.map {
            NavigationBarItem(
                icon = { Icon(imageVector = it.icon, contentDescription = it.label) },
                label = { Text(it.label) },
                selected = currentRoute == it.screen.route,
                onClick = {
                    navHostController.navigate(it.screen.route) {
                    popUpTo(Screen.Home.route) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                } },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AlmostBlack,
                    selectedTextColor = AlmostBlack,
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.LightGray,
                    indicatorColor = Lime01
                )
            )
        }
    }
}