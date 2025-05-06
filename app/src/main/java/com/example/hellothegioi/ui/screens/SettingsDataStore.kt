package com.example.hellothegioi.ui.screens

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.hellothegioi.ui.theme.FontWeightOption
import com.example.hellothegioi.ui.theme.LightNavyBlue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Tạo DataStore singleton
val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {

    // Các keys cho các cài đặt
    private object PreferencesKeys {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val PRIMARY_COLOR = longPreferencesKey("primary_color")
        val FONT_SIZE = intPreferencesKey("font_size")
        val FONT_WEIGHT = stringPreferencesKey("font_weight")
    }

    // Lấy cài đặt theme
    val isDarkTheme: Flow<Boolean> = context.settingsDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.DARK_THEME] ?: false
        }

    // Lấy cài đặt màu sắc
    val primaryColor: Flow<Color> = context.settingsDataStore.data
        .map { preferences ->
            val colorLong = preferences[PreferencesKeys.PRIMARY_COLOR] ?: LightNavyBlue.value.toLong()
            Color(colorLong.toULong())
        }

    // Lấy cài đặt font size
    val fontSize: Flow<Int> = context.settingsDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.FONT_SIZE] ?: 16
        }

    // Lấy cài đặt font weight
    val fontWeight: Flow<FontWeight> = context.settingsDataStore.data
        .map { preferences ->
            val weightStr = preferences[PreferencesKeys.FONT_WEIGHT] ?: FontWeightOption.NORMAL.name
            try {
                FontWeightOption.valueOf(weightStr).weight
            } catch (e: Exception) {
                FontWeight.Normal
            }
        }

    // Cập nhật theme
    suspend fun updateDarkTheme(isDark: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.DARK_THEME] = isDark
        }
    }

    // Cập nhật màu sắc
    suspend fun updatePrimaryColor(color: Color) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.PRIMARY_COLOR] = color.value.toLong()
        }
    }

    // Cập nhật font size
    suspend fun updateFontSize(size: Int) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.FONT_SIZE] = size
        }
    }

    // Cập nhật font weight
    suspend fun updateFontWeight(weight: FontWeightOption) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.FONT_WEIGHT] = weight.name
        }
    }
}