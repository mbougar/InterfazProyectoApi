package com.es.interfazproyectoapi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.es.interfazproyectoapi.data.model.Book
import com.es.interfazproyectoapi.data.network.RetrofitInstance
import coil.compose.rememberImagePainter
import com.es.interfazproyectoapi.ui.components.Header
import com.es.interfazproyectoapi.ui.components.ErrorScreen
import eu.wewox.textflow.TextFlow
import eu.wewox.textflow.TextFlowObstacleAlignment
import kotlinx.coroutines.launch

@Composable
fun BooksScreen(navController: NavController) {
    var books by remember { mutableStateOf<List<Book>?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                books = RetrofitInstance.api.getBooks()
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = "Error al cargar los libros. Verifique su conexión a internet."
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when {
                    errorMessage != null -> {
                        ErrorScreen(message = errorMessage ?: "Error desconocido") {
                            coroutineScope.launch {
                                try {
                                    books = RetrofitInstance.api.getBooks()
                                    errorMessage = null
                                } catch (e: Exception) {
                                    errorMessage = "Error al cargar los libros. Verifique su conexión a internet."
                                }
                            }
                        }
                    }
                    books == null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    else -> {
                        LazyColumn(modifier = Modifier.padding(16.dp)) {
                            itemsIndexed(books!!) { index, book ->
                                BookItem(book)
                                if (index != books!!.lastIndex) {
                                    HorizontalDivider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        topBar = {
            Header(
                "Libros",
                navController
            )
        }
    )
}

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