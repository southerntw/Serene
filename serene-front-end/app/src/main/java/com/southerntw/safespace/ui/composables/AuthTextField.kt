package com.southerntw.safespace.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.White

@Composable
fun AuthTextField(modifier: Modifier = Modifier,
    input: String,
    onInputChanged: (String) -> Unit,
    label: String,
    isPassword: Boolean = false
) {
    OutlinedTextField(label = { Text(label) },
        value = input,
        onValueChange = onInputChanged,
        modifier = modifier.fillMaxWidth().padding(bottom = 16.dp),
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
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}