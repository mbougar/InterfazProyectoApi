package com.es.interfazproyectoapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.es.interfazproyectoapi.data.Settings
import com.es.interfazproyectoapi.data.SettingsDataStore
import com.es.interfazproyectoapi.navigation.AppNavigation
import com.es.interfazproyectoapi.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsDataStore = SettingsDataStore(this)

        setContent {
            MainContent(settingsDataStore)
        }
    }
}

@Composable
fun MainContent(settingsDataStore: SettingsDataStore) {
    val settings by settingsDataStore.settingsFlow.collectAsState(
        initial = Settings("System Default", true, true)
    )

    val darkTheme = when (settings.theme) {
        "Claro" -> false
        "Oscuro" -> true
        else -> isSystemInDarkTheme()
    }

    AppTheme(darkTheme = darkTheme) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).background(MaterialTheme.colorScheme.background)) {
                val navController = rememberNavController()
                AppNavigation(navController = navController, settingsDataStore)
            }
        }
    }
}