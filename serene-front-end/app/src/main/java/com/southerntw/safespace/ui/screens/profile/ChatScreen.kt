package com.southerntw.safespace.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.southerntw.safespace.R

import com.southerntw.safespace.ui.composables.ChatBox
import com.southerntw.safespace.ui.navigation.screen.Screen
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.chatBlack
import com.southerntw.safespace.ui.theme.chatForeground
import com.southerntw.safespace.ui.theme.chatGreen
import com.southerntw.safespace.util.ChatUiState
import com.southerntw.safespace.viewmodel.ChatViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val viewModel: ChatViewModel = hiltViewModel()
    val chatData by viewModel.chatData.collectAsState()
    val chatMsg by viewModel.chatMsg // Access the chatMsg as it is a state, not a flow
    val chatResponses by viewModel.chatResponses.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        viewModel.addInitialMessage("Hello there, can I do anything you wanna talk about today?")
    }

    SideEffect {
        viewModel.tryUserId()
    }

    ChatContent(
        message = chatMsg,
        chatData = chatData,
        onUserChat = { viewModel.updateChatMsg(it) },
        onUserSend = {
            viewModel.sendMessage()
            viewModel.updateChatMsg("")
        },
        onDoneClicked = {
            navHostController.navigate(Screen.ChatResult.route)
        },
        isLoading = chatResponses is ChatUiState.Load
    )
}

@Composable
fun ChatContent(
    message: String,
    chatData: List<com.southerntw.safespace.data.model.ChatData>,
    onUserChat: (String) -> Unit,
    onUserSend: () -> Unit,
    onDoneClicked: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(chatData.size) {
        listState.animateScrollToItem(chatData.size)
    }

    Box(modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 32.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 34.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(model = R.drawable.logo, contentDescription = "App Logo", modifier = modifier.size(36.dp))
                Text("Chat", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium), color = AlmostBlack)
                Spacer(modifier = Modifier.weight(1F))
                Text("Done chatting >>", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium), color = Color.LightGray, modifier = modifier.clickable { onDoneClicked() })
            }
            LazyColumn(state = listState, modifier = modifier.fillMaxWidth().padding(bottom = 100.dp).fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                items(chatData.size) { index ->
                    val chat = chatData[index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = if (chat.isUser) Arrangement.End else Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(
                                        topStart = 24.dp,
                                        topEnd = 24.dp,
                                        bottomEnd = if (chat.isUser) 0.dp else 24.dp,
                                        bottomStart = if (chat.isUser) 24.dp else 0.dp
                                    )
                                )
                                .background(if (chat.isUser) chatBlack else chatGreen)
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        ) {
                            Text(text = chat.message, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = chatForeground)
                        }
                    }
                }
                item {
                    if (isLoading) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(24.dp))
                                    .background(chatGreen)
                                    .padding(horizontal = 16.dp, vertical = 12.dp)
                            ) {
                                Text(text = "...", style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = chatForeground)
                            }
                        }
                    }
                }
            }
        }
            ChatBox(modifier = modifier.align(Alignment.BottomCenter), chat = message, onChatChange = onUserChat, onChatSend = onUserSend)
    }
}
