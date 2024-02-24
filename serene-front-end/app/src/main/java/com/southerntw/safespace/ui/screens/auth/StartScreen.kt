package com.southerntw.safespace.ui.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.ButtonFilled
import com.southerntw.safespace.ui.composables.ButtonOutlined
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.White

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    StartContent(modifier = modifier, onSignInClicked = {
        navHostController.navigate(Screen.SignIn.route) {
            popUpTo(Screen.SignIn.route) {
                inclusive = true
            }
        }
    }, onSignUpClicked = {
        navHostController.navigate(Screen.SignUp.route) {
            popUpTo(Screen.SignUp.route) {
                inclusive = true
            }
        }
    }, onGuestClicked = {
        navHostController.navigate(Screen.Home.route) {
            popUpTo(0)
        }
    })
}

@Composable
fun StartContent(modifier: Modifier, onSignInClicked: () -> Unit, onSignUpClicked: () -> Unit, onGuestClicked: () -> Unit) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
        AsyncImage(model = R.drawable.logo, contentDescription = "Safespace logo", modifier = Modifier
            .height(24.dp)
            .width(24.dp)
        )
        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "Welcome to Safespace", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(bottom = 12.dp), color = MaterialTheme.colorScheme.onBackground)
        Text(text = "A place where you can be yourself", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)

        Spacer(Modifier.weight(1F))

        AsyncImage(model = R.drawable.illust_home, contentDescription = "Illustration home", modifier = Modifier
            .height(379.dp)
            .width(336.dp))

        Spacer(Modifier.weight(1F))

        ButtonFilled(modifier = Modifier, onClicked = onSignInClicked, text = "Sign In")
        ButtonOutlined(modifier = Modifier, onClicked = onSignUpClicked, text = "Sign Up")

        TextButton(onClick = onGuestClicked) {
            Text(text = "continue as a guest...", style = MaterialTheme.typography.labelSmall, color = AlmostBlack)
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}