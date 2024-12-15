package com.es.interfazproyectoapi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.es.interfazproyectoapi.data.network.RetrofitInstance
import com.es.interfazproyectoapi.data.model.Character
import com.es.interfazproyectoapi.ui.components.Header
import com.es.interfazproyectoapi.ui.components.ErrorScreen
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter

@Composable
fun CharactersScreen(navController: NavController) {
    var characters by remember { mutableStateOf<List<Character>?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                characters = RetrofitInstance.api.getCharacters()
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = "Error al cargar los personajes. Verifique su conexión a internet."
            }
        }
    }

    Scaffold(
        topBar = {
            Header(
                "Personajes",
                navController
            )
        },
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
                                    characters = RetrofitInstance.api.getCharacters()
                                    errorMessage = null
                                } catch (e: Exception) {
                                    errorMessage = "Error al cargar los personajes. Verifique su conexión a internet."
                                }
                            }
                        }
                    }
                    characters == null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    else -> {
                        LazyColumn(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(characters!!) { character ->
                                CharacterItem(character)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun CharacterItem(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(data = character.image),
                contentDescription = "Imagen de ${character.fullName}",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = character.fullName,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Apodo: ${character.nickname ?: "Sin apodo"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Casa: ${character.hogwartsHouse ?: "Sin casa"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Interpretado por: ${character.interpretedBy ?: "Desconocido"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                if (!character.children.isNullOrEmpty()) {
                    Text(
                        text = "Hijos: ${character.children.joinToString(", ")}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    text = "Fecha de nacimiento: ${character.birthdate ?: "Desconocida"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}