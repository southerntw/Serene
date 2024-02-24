package com.southerntw.safespace.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.White

@Composable
fun ChatBox(modifier: Modifier, chat: String, onChatChange: (String) -> Unit, onChatSend: () -> Unit) {
    Box(modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp).background(White)) {
        Row(modifier = modifier.fillMaxWidth().padding(bottom = 16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(
                value = chat,
                onValueChange = onChatChange,
                maxLines = 3,
                placeholder = { Text("Send a message to AI") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = AlmostBlack,
                    focusedIndicatorColor = AlmostBlack,
                    focusedContainerColor = White,
                    focusedLabelColor = AlmostBlack,
                    unfocusedIndicatorColor = AlmostBlack,
                    unfocusedContainerColor = White,
                    unfocusedLabelColor = AlmostBlack,
                    cursorColor = AlmostBlack
                ),
                modifier = Modifier.weight(1F)
            )
            IconButton(onClick = { onChatSend() }, modifier = modifier.padding(start = 4.dp)) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_send),
                    contentDescription = "Send Message",
                    tint = AlmostBlack,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}