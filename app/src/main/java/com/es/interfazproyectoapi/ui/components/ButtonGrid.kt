package com.es.interfazproyectoapi.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ButtonGrid(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ApiButton("Personajes") { navController.navigate("characters") }
            ApiButton("Hechizos") { navController.navigate("spells") }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ApiButton("Libros") { navController.navigate("books") }
            ApiButton("Casas") { navController.navigate("houses") }
        }
    }
}