package com.es.interfazproyectoapi.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.es.interfazproyectoapi.data.SettingsDataStore
import com.es.interfazproyectoapi.ui.screens.ApiScreen
import com.es.interfazproyectoapi.ui.screens.BlogScreen
import com.es.interfazproyectoapi.ui.screens.CharactersScreen
import com.es.interfazproyectoapi.ui.screens.GuiaScreen
import com.es.interfazproyectoapi.ui.screens.PortadaScreen
import com.es.interfazproyectoapi.ui.screens.SobreNosotrosScreen
import com.es.interfazproyectoapi.ui.screens.SpellsScreen
import com.es.interfazproyectoapi.ui.screens.BooksScreen
import com.es.interfazproyectoapi.ui.screens.HousesScreen
import com.es.interfazproyectoapi.ui.screens.SettingsScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Composable
fun AppNavigation(navController: NavHostController, settingsDataStore: SettingsDataStore) {
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
        composable("settings") { SettingsScreen(navController, settingsDataStore) }
    }
}