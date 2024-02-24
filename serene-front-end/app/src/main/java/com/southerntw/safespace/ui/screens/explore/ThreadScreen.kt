package com.southerntw.safespace.ui.screens.explore

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.composables.ThreadComment
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.GrayBackground
import com.southerntw.safespace.ui.theme.GrayOnBackground
import com.southerntw.safespace.ui.theme.White
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

@Composable
fun ThreadScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    ThreadContent(modifier = modifier, threadTitle = "I am not feeling great today.. Help!", threadDescription = "By somebody, 12 December 2023 at 3:30", threadContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
        userComment = "", onCommentChanged = {}, onSendClicked = {})
}

@Composable
fun ThreadContent(modifier: Modifier, threadTitle: String, threadDescription: String, threadContent: String, userComment: String, onCommentChanged: (String) -> Unit, onSendClicked: () -> Unit) {
        Column(
            Modifier
                .fillMaxSize()
                .background(GrayBackground)
                .verticalScroll(rememberScrollState()))
        {
            Column(
                modifier
                    .background(White)
                    .padding(top = 38.dp, start = 28.dp, end = 28.dp, bottom = 24.dp)) {
                Text(threadTitle, color = AlmostBlack, style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, lineHeight = 36.sp), modifier = modifier.padding(bottom = 8.dp))
                Text(threadDescription, color = Color.LightGray, style = MaterialTheme.typography.bodySmall, modifier = modifier.padding(bottom = 32.dp))

                Text(threadContent, color = AlmostBlack, style = MaterialTheme.typography.titleSmall)
            }
            Canvas(modifier = Modifier
                .size(1.dp, 32.dp)
                .align(Alignment.CenterHorizontally)) {
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                drawLine(
                    color = GrayOnBackground,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 2f,
                    pathEffect = pathEffect
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 24.dp)) {
                ThreadComment(commentName = "Someone", commentDescription = "13 December 2023 at 3.30", commentContent = "I don't think that's true. Hang in there.")
                ThreadComment(commentName = "Someone", commentDescription = "13 December 2023 at 3.30", commentContent = "I don't think that's true. Hang in there. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. ")
                ThreadComment(commentName = "Someone", commentDescription = "13 December 2023 at 3.30", commentContent = "I don't think that's true. Hang in there. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. ")
                ThreadComment(commentName = "Someone", commentDescription = "13 December 2023 at 3.30", commentContent = "I don't think that's true. Hang in there. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. Hang in. ")
                Box(modifier.fillMaxWidth().background(White)) {
                    Column(modifier.fillMaxWidth()) {
                        OutlinedTextField(label = { Text("Leave a comment!") }, value = userComment, onValueChange = onCommentChanged, minLines =  5,
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = AlmostBlack,
                                focusedIndicatorColor = White,
                                focusedContainerColor = White,
                                focusedLabelColor = AlmostBlack,
                                unfocusedIndicatorColor = White,
                                unfocusedContainerColor = White,
                                unfocusedLabelColor = AlmostBlack,
                                cursorColor = AlmostBlack
                            ),
                            modifier = modifier.fillMaxWidth()
                        )
                        IconButton(onClick = { onSendClicked() }) {
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
        }
}