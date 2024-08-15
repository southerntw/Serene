package com.southerntw.safespace

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.southerntw.safespace.ui.composables.BottomBar
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.screens.auth.FillDataScreen
import com.southerntw.safespace.ui.screens.auth.OnBoardingScreen
import com.southerntw.safespace.ui.screens.profile.SettingsScreen
import com.southerntw.safespace.ui.screens.auth.SignInScreen
import com.southerntw.safespace.ui.screens.auth.SignUpScreen
import com.southerntw.safespace.ui.screens.auth.StartScreen
import com.southerntw.safespace.ui.screens.explore.ExploreScreen
import com.southerntw.safespace.ui.screens.explore.MoreNewsScreen
import com.southerntw.safespace.ui.screens.explore.MoreThreadsScreen
import com.southerntw.safespace.ui.screens.explore.NewsScreen
import com.southerntw.safespace.ui.screens.explore.ThreadScreen
import com.southerntw.safespace.ui.screens.home.HomeScreen
import com.southerntw.safespace.ui.screens.profile.ChatResultScreen
import com.southerntw.safespace.ui.screens.profile.ChatScreen
import com.southerntw.safespace.ui.screens.profile.ProfileScreen
import com.southerntw.safespace.ui.screens.profile.SettingsEditScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainJetpack(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Explore.route || currentRoute == Screen.Profile.route) {
                BottomBar(navHostController = navHostController, name = "Profile")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Start.route,
            modifier = Modifier.padding(
                top = if (
                    currentRoute == Screen.SignIn.route ||
                    currentRoute == Screen.SignUp.route ||
                    currentRoute == Screen.OnBoarding.route ||
                    currentRoute == Screen.Start.route
                ) 0.dp else innerPadding.calculateTopPadding(),
                bottom = if (
                    currentRoute == Screen.OnBoarding.route
                ) 0.dp else innerPadding.calculateBottomPadding(),
            )
        ) {
            val innerTopPadding = innerPadding.calculateTopPadding()

            composable(Screen.Start.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                }
            ) {
                StartScreen(navHostController = navHostController, modifier = modifier.padding(top = innerTopPadding))
            }
            composable(Screen.SignIn.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                }
            ) {
                SignInScreen(modifier = modifier, navHostController = navHostController)
            }
            composable(Screen.SignUp.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                }
            ) {
                SignUpScreen(navHostController = navHostController)
            }
            composable(Screen.OnBoarding.route,
                enterTransition = {
                    scaleIn(
                        animationSpec = tween(500, delayMillis = 90),
                        initialScale = 0.8f
                    ) + fadeIn(animationSpec = tween(500, delayMillis = 90))
                }
            ) {
                OnBoardingScreen(navHostController = navHostController)
            }
            composable(Screen.FillData.route) {
                FillDataScreen(navHostController = navHostController)
            }
            composable(Screen.Home.route
            ) {
                HomeScreen(navHostController = navHostController)
            }
            composable(Screen.Explore.route
            ) {
                ExploreScreen(navHostController = navHostController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navHostController = navHostController)
            }
            composable(Screen.Thread.route,
                arguments = listOf(navArgument("threadId") { type = NavType.IntType }),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(400)
                    )
                }
            ) { backStackEntry ->
                val threadId = backStackEntry.arguments?.getInt("threadId") ?: 0
                ThreadScreen(navHostController = navHostController, threadId = threadId)
            }
            composable(Screen.News.route,
                arguments = listOf(navArgument("newsId") { type = NavType.IntType }),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(400)
                    )
                }
            ) { backStackEntry ->
                val newsId = backStackEntry.arguments?.getInt("newsId") ?: 0
                NewsScreen(navHostController = navHostController, newsId = newsId)
            }
            composable(Screen.MoreThreads.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(400)
                    )
                }
            ) {
                MoreThreadsScreen(navHostController = navHostController)
            }
            composable(Screen.MoreNews.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(400)
                    )
                }
            ) {
                MoreNewsScreen(navHostController = navHostController)
            }
            composable(Screen.Chat.route) {
                ChatScreen(navHostController = navHostController)
            }
            composable(Screen.ChatResult.route) {
                ChatResultScreen(navHostController = navHostController)
            }
            composable(Screen.Settings.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                }
            ) {
                SettingsScreen(navHostController = navHostController)
            }
            composable(Screen.Edit.route) {
                SettingsEditScreen(navHostController = navHostController)
            }
        }
    }
}