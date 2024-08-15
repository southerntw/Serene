package com.southerntw.safespace.ui.navigation.screen

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object OnBoarding : Screen("onboarding")
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Profile : Screen("profile")

    // TODO: Routes?
    object Thread : Screen("thread/{threadId}") {
        fun createRoute(threadId: Int) = "thread/$threadId"
    }
    object News : Screen("news/{newsId}") {
        fun createRoute(newsId: Int) = "news/$newsId"
    }

    object MoreThreads : Screen("morethreads")
    object MoreNews : Screen("morenews")

    object Chat : Screen("chat")
    object ChatResult : Screen("chatresult")

    object Settings : Screen("settings")
    object Edit : Screen("edit")

    object FillData : Screen("filldata")

}