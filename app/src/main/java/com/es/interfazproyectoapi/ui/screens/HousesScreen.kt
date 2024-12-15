package com.es.interfazproyectoapi.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.es.interfazproyectoapi.data.network.RetrofitInstance
import com.es.interfazproyectoapi.data.model.House
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
fun HousesScreen(navController: NavController) {
    var houses by remember { mutableStateOf<List<House>?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                houses = RetrofitInstance.api.getHouses()
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = "Error al cargar los datos. Verifique su conexión a internet."
            }
        }
    }

    Scaffold(
        topBar = {
            Header(
                "Casas",
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
                                    houses = RetrofitInstance.api.getHouses()
                                    errorMessage = null
                                } catch (e: Exception) {
                                    errorMessage = "Error al cargar los datos. Verifique su conexión a internet."
                                }
                            }
                        }
                    }
                    houses == null -> {
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
                            itemsIndexed(houses!!) { index, house ->
                                HouseItem(house)
                                if (index != houses!!.lastIndex) {
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
fun HouseItem(house: House) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "${house.emoji ?: ""} ${house.house}",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Fundador: ${house.founder ?: "Desconocido"}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Animal: ${house.animal ?: "No definido"}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        if (!house.colors.isNullOrEmpty()) {
            Text(
                text = "Colores: ${house.colors.joinToString(", ")}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}