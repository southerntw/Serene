package com.southerntw.safespace.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.southerntw.safespace.R
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.Gray02
import com.southerntw.safespace.ui.theme.GrayText
import com.southerntw.safespace.ui.theme.SafespaceTheme

@Composable
fun HomeEntry(modifier: Modifier = Modifier, headerImage: Int,
              entryName: String,
              entryDescription: String,
              onClicked: () -> Unit
) {
    Box(modifier.width(336.dp).height(161.dp).clickable { onClicked() }) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier.fillMaxSize().padding(horizontal = 12.dp)) {
            AsyncImage(model = headerImage, contentDescription = entryName, modifier = modifier.width(336.dp).height(97.dp))
            Text(entryName, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium), color = AlmostBlack)
            Text(entryDescription, style = MaterialTheme.typography.labelSmall, color = Gray02)
        }
    }
}

@Preview(
    device = "id:pixel_5",
    showSystemUi = true,
    showBackground = true
)
@Composable
fun HomeEntryPreview() {
    SafespaceTheme {
        HomeEntry(headerImage = R.drawable.illust_login, entryName = "Have a chat!", entryDescription = "With our AI companion.", onClicked = {})
    }
}