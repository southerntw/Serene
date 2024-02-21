package com.southerntw.safespace.ui.navigation.screen

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object OnBoarding : Screen("onboarding")
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Profile : Screen("profile")
}