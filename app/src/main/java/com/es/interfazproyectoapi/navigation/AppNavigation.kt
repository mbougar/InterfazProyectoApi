package com.es.interfazproyectoapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.es.interfazproyectoapi.ui.screens.ApiScreen
import com.es.interfazproyectoapi.ui.screens.BlogScreen
import com.es.interfazproyectoapi.ui.screens.CharactersScreen
import com.es.interfazproyectoapi.ui.screens.GuiaScreen
import com.es.interfazproyectoapi.ui.screens.PortadaScreen
import com.es.interfazproyectoapi.ui.screens.SobreNosotrosScreen
import com.es.interfazproyectoapi.ui.screens.SpellsScreen
import com.es.interfazproyectoapi.ui.screens.BooksScreen
import com.es.interfazproyectoapi.ui.screens.HousesScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "portada") {
        composable("portada") { PortadaScreen(navController) }
        composable("blog") { BlogScreen(navController) }
        composable("guia") { GuiaScreen(navController) }
        composable("api") { ApiScreen(navController) }
        composable("characters") { CharactersScreen(navController) }
        composable("spells") { SpellsScreen(navController) }
        composable("books") { BooksScreen(navController) }
        composable("houses") { HousesScreen(navController) }
        composable("about") { SobreNosotrosScreen(navController) }
    }
}