package com.es.interfazproyectoapi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.es.interfazproyectoapi.data.Settings
import com.es.interfazproyectoapi.data.SettingsDataStore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController, settingsDataStore: SettingsDataStore) {
    val settings by settingsDataStore.settingsFlow.collectAsState(
        initial = Settings("System Default", true, true)
    )
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors()
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Tema",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        var isThemeDropdownExpanded by remember { mutableStateOf(false) }
                        Box {
                            OutlinedButton(
                                onClick = { isThemeDropdownExpanded = !isThemeDropdownExpanded }
                            ) {
                                Text(
                                    text = settings.theme,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Icon(
                                    imageVector = if (isThemeDropdownExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Toggle Theme Dropdown"
                                )
                            }
                            DropdownMenu(
                                expanded = isThemeDropdownExpanded,
                                onDismissRequest = { isThemeDropdownExpanded = false }
                            ) {
                                listOf("Claro", "Oscuro", "Predeterminado del sistema").forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text(option) },
                                        onClick = {
                                            coroutineScope.launch {
                                                settingsDataStore.saveTheme(option)
                                            }
                                            isThemeDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Permitir Notificaciones",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Switch(
                            checked = settings.allowNotifications,
                            onCheckedChange = {
                                coroutineScope.launch {
                                    settingsDataStore.saveAllowNotifications(it)
                                }
                            }
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Permitir informes de error",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Switch(
                            checked = settings.crashReporting,
                            onCheckedChange = {
                                coroutineScope.launch {
                                    settingsDataStore.saveCrashReporting(it)
                                }
                            }
                        )
                    }

                    Divider(modifier = Modifier.fillMaxWidth())

                    TextButton(
                        onClick = { /* Futuro link a politica */ }
                    ) {
                        Text(
                            text = "Política de privacidad",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "App Version: 1.1.0",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    )
}