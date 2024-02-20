package com.southerntw.safespace.ui.navigation.screen

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
}