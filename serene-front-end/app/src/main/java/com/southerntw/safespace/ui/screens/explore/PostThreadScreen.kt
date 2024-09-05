package com.southerntw.safespace.ui.screens.explore

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.util.AuthUiState
import com.southerntw.safespace.viewmodel.PostViewModel

@Composable
fun PostThreadScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    postViewModel: PostViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        postViewModel.tryUserId()
        postViewModel.tryUserToken()
    }

    var title by remember { mutableStateOf(TextFieldValue()) }
    var content by remember { mutableStateOf(TextFieldValue()) }

    val postThreadState by postViewModel.postThreadState.collectAsState()

    PostThreadContent(
        modifier = modifier,
        title = title,
        onTitleChanged = { title = it },
        content = content,
        onContentChanged = { content = it },
        onCancelClicked = { postViewModel.resetPostThreadState() },  // Reset the state on cancel
        onPostClicked = {
            postViewModel.postThread(
                title = title.text,
                text = content.text
            )
        }
    )

    when (postThreadState) {
        is AuthUiState.Load -> {
            PostThreadContent(
                // Display loading indicator
                modifier = modifier.alpha(0.7F),
                title = title,
                onTitleChanged = { title = it },
                content = content,
                onContentChanged = { content = it },
                onCancelClicked = { },  // Reset the state on cancel
                onPostClicked = { }
            )
        }
        is AuthUiState.Success -> {
            // Navigate back on success
            LaunchedEffect(Unit) {
                navController.popBackStack()
            }
        }
        is AuthUiState.Failure -> {
            // Handle failure (e.g., show an error message)
            val errorMessage = "error."
            // Display the error message
        }
        else -> Unit // Do nothing in Idle state
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostThreadContent(
    modifier: Modifier = Modifier,
    title: TextFieldValue,
    onTitleChanged: (TextFieldValue) -> Unit,
    content: TextFieldValue,
    onContentChanged: (TextFieldValue) -> Unit,
    onCancelClicked: () -> Unit,
    onPostClicked: () -> Unit
) {
    Column() {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 28.dp, end = 28.dp, bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically // Align items in the center vertically
        ) {
            Text(
                "Post New Thread",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = AlmostBlack,
                modifier = Modifier.weight(1f) // Let the Text take as much space as possible
            )
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title TextField
            TextField(
                value = title,
                onValueChange = onTitleChanged,
                label = { Text("Thread Title") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = AlmostBlack,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = AlmostBlack
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Content TextField
            TextField(
                value = content,
                onValueChange = onContentChanged,
                label = { Text("Content") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = AlmostBlack,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = AlmostBlack
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // You can adjust the height as needed
                    .padding(bottom = 32.dp),
                singleLine = false,
                maxLines = 10
            )

            // Buttons for Cancel and Post
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onCancelClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Cancel")
                }

                Spacer(modifier = Modifier.width(8.dp)) // Add spacing between the buttons

                Button(
                    onClick = onPostClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = AlmostBlack)
                ) {
                    Text("Post")
                }
            }
        }
    }
}