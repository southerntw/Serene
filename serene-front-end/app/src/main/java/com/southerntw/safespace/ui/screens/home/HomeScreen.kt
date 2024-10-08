package com.southerntw.safespace.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.HomeEntry
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    HomeContent(modifier = Modifier, name = "Matilda",
        onCommunityClicked = {
            navHostController.navigate(Screen.MoreThreads.route)
        },
        onNewsClicked = {
            navHostController.navigate(Screen.MoreNews.route)
        },
        onChatClicked = {
            navHostController.navigate(Screen.Chat.route)
        }
    )
}

@Composable
fun HomeContent(modifier: Modifier, name: String, onCommunityClicked: () -> Unit, onNewsClicked: () -> Unit, onChatClicked: () -> Unit) {
    Column(
        modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 28.dp, end = 28.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Greetings!", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = AlmostBlack)
        Spacer(modifier.height(54.dp))
        Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(26.dp)) {
            HomeEntry(headerImage = R.drawable.entry_header1, entryName = "Have a Chat!", entryDescription = "With our AI companion!", onClicked = onChatClicked)
            HomeEntry(headerImage = R.drawable.entry_header2, entryName = "Check out the community", entryDescription = "And find somebody alike.", onClicked = onCommunityClicked)
            HomeEntry(headerImage = R.drawable.entry_header3, entryName = "Latest news on Mental Health", entryDescription = "Keep up to date to recent informations.", onClicked = onNewsClicked)
        }
    }
}