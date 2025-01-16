package com.es.interfazproyectoapi.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {

    companion object {
        val THEME = stringPreferencesKey("theme")
        val ALLOW_NOTIFICATIONS = booleanPreferencesKey("allow_notifications")
        val CRASH_REPORTING = booleanPreferencesKey("crash_reporting")
    }

    val settingsFlow: Flow<Settings> = context.dataStore.data.map { preferences ->
        Settings(
            theme = preferences[THEME] ?: "System Default",
            allowNotifications = preferences[ALLOW_NOTIFICATIONS] ?: true,
            crashReporting = preferences[CRASH_REPORTING] ?: true
        )
    }

    suspend fun saveTheme(theme: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME] = theme
        }
    }

    suspend fun saveAllowNotifications(allow: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ALLOW_NOTIFICATIONS] = allow
        }
    }

    suspend fun saveCrashReporting(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[CRASH_REPORTING] = enabled
        }
    }
}

data class Settings(
    val theme: String,
    val allowNotifications: Boolean,
    val crashReporting: Boolean
)