package com.southerntw.safespace.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.ChatBox
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.chatBlack
import com.southerntw.safespace.ui.theme.chatForeground
import com.southerntw.safespace.ui.theme.chatGreen

@Composable
fun ChatScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    ChatContent(userChat = "", onUserChat = {}, onUserSend = {}, message = "Welcome to Safespace, Matilda!",
        onDoneClicked = {
            navHostController.navigate(Screen.ChatResult.route)
        })
}

@Composable
fun ChatContent(modifier: Modifier = Modifier, message: String, userChat: String, onUserChat: (String) -> Unit, onUserSend: () -> Unit, onDoneClicked: () -> Unit) {
    Box(modifier.fillMaxSize()) {
        ChatBox(modifier = modifier.align(Alignment.BottomCenter), chat = userChat, onChatChange = onUserChat, onChatSend = onUserSend)
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 32.dp))
        {
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 44.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(model = R.drawable.logo, contentDescription = "App Logo", modifier = modifier.size(36.dp))
                Text("Chat", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium), color = AlmostBlack)

                Spacer(modifier = Modifier.weight(1F))

                Text("Done chatting >>", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium), color = Color.LightGray, modifier = modifier.clickable { onDoneClicked() })
            }
            Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Start) // TODO: Change
                        .clip(
                            RoundedCornerShape(
                                topStart = 48f,
                                topEnd = 48f,
                                bottomEnd = 48f, // TODO: Change
                            )
                        )
                        .background(chatGreen)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    // TODO: Change this
                    Text(text = message, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp) , color = chatForeground)
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.Start) // TODO: Change
                        .clip(
                            RoundedCornerShape(
                                topStart = 48f,
                                topEnd = 48f,
                                bottomEnd = 48f, // TODO: Change
                            )
                        )
                        .background(chatGreen)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    // TODO: Change this
                    Text(text = "In this app, you can do many things!", style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = chatForeground)
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.End) // TODO: Change
                        .clip(
                            RoundedCornerShape(
                                topStart = 48f,
                                topEnd = 48f,
                                bottomStart = 48f, // TODO: Change
                            )
                        )
                        .background(chatBlack)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    // TODO: Change this
                    Text(text = "Tell me more!", style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = chatForeground)
                }
            }
        }
    }
}