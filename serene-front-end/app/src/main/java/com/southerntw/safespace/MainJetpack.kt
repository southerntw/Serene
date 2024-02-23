package com.southerntw.safespace

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.southerntw.safespace.ui.composables.BottomBar
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.screens.auth.OnBoardingScreen
import com.southerntw.safespace.ui.screens.auth.SignInScreen
import com.southerntw.safespace.ui.screens.auth.SignUpScreen
import com.southerntw.safespace.ui.screens.auth.StartScreen
import com.southerntw.safespace.ui.screens.explore.ExploreScreen
import com.southerntw.safespace.ui.screens.explore.ThreadScreen
import com.southerntw.safespace.ui.screens.home.HomeScreen
import com.southerntw.safespace.ui.screens.profile.ProfileScreen
import com.southerntw.safespace.ui.theme.SafespaceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainJetpack(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    Greeting("Android")

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Explore.route || currentRoute == Screen.Profile.route) {
                BottomBar(navHostController = navHostController, name = "Matilda")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Start.route,
            modifier = Modifier.padding(
                top = if (
                    currentRoute == Screen.SignIn.route ||
                    currentRoute == Screen.SignUp.route
                ) 0.dp else innerPadding.calculateTopPadding(),
                bottom = if (
                    false
                ) 0.dp else innerPadding.calculateBottomPadding(),
            )
        ) {
            composable(Screen.Start.route) {
                StartScreen(navHostController = navHostController)
            }
            composable(Screen.SignIn.route) {
                SignInScreen(modifier = modifier, navHostController = navHostController)
            }
            composable(Screen.SignUp.route) {
                SignUpScreen(navHostController = navHostController)
            }
            composable(Screen.OnBoarding.route) {
                OnBoardingScreen(navHostController = navHostController)
            }
            composable(Screen.Home.route) {
                HomeScreen(navHostController = navHostController)
            }
            composable(Screen.Explore.route) {
                ExploreScreen(navHostController = navHostController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navHostController = navHostController)
            }
            composable(Screen.Thread.route) {
                ThreadScreen(navHostController = navHostController)
            }
        }
    }
}
