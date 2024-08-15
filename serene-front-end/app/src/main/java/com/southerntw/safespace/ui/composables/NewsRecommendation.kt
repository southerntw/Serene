package com.southerntw.safespace.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.southerntw.safespace.ui.theme.AlmostBlack
import com.southerntw.safespace.ui.theme.White

@Composable
fun NewsRecommendation(modifier: Modifier = Modifier, newsHeader: String?, newsTitle: String?) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp)
            .background(White)
    ) {
         Column {
            AsyncImage(
                model = newsHeader,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
             if (newsTitle != null) {
                 Text(
                     text = newsTitle,
                     style = MaterialTheme.typography.bodySmall,
                     color = AlmostBlack,
                     textAlign = TextAlign.Center,
                     modifier = modifier.padding(top = 8.dp)
                 )
             }
        }
    }
}