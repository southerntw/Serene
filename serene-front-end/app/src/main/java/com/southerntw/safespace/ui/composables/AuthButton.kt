package com.southerntw.safespace.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.White

@Composable
fun ButtonFilled(modifier: Modifier, onClicked: () -> Unit, text: String) {
    Button(onClick = onClicked, modifier = modifier
        .width(288.dp)
        .height(56.dp)
        .padding(bottom = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AlmostBlack,
            contentColor = AlmostBlack,
            disabledContainerColor = AlmostBlack,
            disabledContentColor = AlmostBlack
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun ButtonOutlined(modifier: Modifier, onClicked: () -> Unit, text: String) {
    Button(onClick = onClicked, modifier = modifier
        .width(288.dp)
        .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = White,
            contentColor = White,
            disabledContainerColor = White,
            disabledContentColor = White
        ),
        border = BorderStroke(1.dp, AlmostBlack)
    ) {
        Text(text = text, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary)
    }
}