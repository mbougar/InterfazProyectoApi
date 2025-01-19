package com.es.interfazproyectoapi.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.es.interfazproyectoapi.data.model.Book
import eu.wewox.textflow.TextFlow
import eu.wewox.textflow.TextFlowObstacleAlignment

@Composable
fun BookItem(book: Book) {
    Text(text = book.title, style = MaterialTheme.typography.headlineSmall)
    Text(text = "(${book.originalTitle})", style = MaterialTheme.typography.bodySmall)

    Column(modifier = Modifier.padding(8.dp)) {

        TextFlow(
            text = buildString {
                appendLine("Páginas: ${book.pages}")
                appendLine("Publicado: ${book.releaseDate}")
                appendLine("Sinopsis: ${book.description}")
            },
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier
                .fillMaxWidth(),
            obstacleAlignment = TextFlowObstacleAlignment.TopStart,
            obstacleContent = {
                Image(
                    painter = rememberImagePainter(data = book.cover),
                    contentDescription = "Portada de ${book.title}",
                    modifier = Modifier
                        .width(120.dp)
                        .height(200.dp)
                        .padding(bottom = 8.dp, end = 8.dp),
                    contentScale = ContentScale.Crop
                )
            }
        )
    }
}