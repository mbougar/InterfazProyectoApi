package com.es.interfazproyectoapi.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.es.interfazproyectoapi.data.network.RetrofitInstance
import com.es.interfazproyectoapi.data.model.Spell
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.Alignment
import com.es.interfazproyectoapi.ui.components.Header
import com.es.interfazproyectoapi.ui.components.ErrorScreen
import kotlinx.coroutines.launch

@Composable
fun SpellsScreen(navController: NavController) {
    var spells by remember { mutableStateOf<List<Spell>?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                spells = RetrofitInstance.api.getSpells()
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = "Error al cargar los hechizos. Verifique su conexión a internet."
            }
        }
    }

    Scaffold(
        topBar = {
            Header(
                "Hechizos",
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
                                    spells = RetrofitInstance.api.getSpells()
                                    errorMessage = null
                                } catch (e: Exception) {
                                    errorMessage = "Error al cargar los hechizos. Verifique su conexión a internet."
                                }
                            }
                        }
                    }
                    spells == null -> {
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
                            itemsIndexed(spells!!) { index, spell ->
                                SpellItem(spell)
                                if (index != spells!!.lastIndex) {
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
        }
    )
}

@Composable
fun SpellItem(spell: Spell) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = spell.spell,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Uso: ${spell.use ?: "Desconocido"}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}