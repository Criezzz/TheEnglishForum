package com.example.hellothegioi.ui.screens

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellothegioi.ui.theme.FontWeightOption
import com.example.hellothegioi.ui.theme.LightNavyBlue
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsDataStore = SettingsDataStore(application)

    // StateFlows để quan sát các cài đặt
    val isDarkTheme: StateFlow<Boolean> = settingsDataStore.isDarkTheme
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val primaryColor: StateFlow<Color> = settingsDataStore.primaryColor
        .stateIn(viewModelScope, SharingStarted.Eagerly, LightNavyBlue)

    val fontSize: StateFlow<Int> = settingsDataStore.fontSize
        .stateIn(viewModelScope, SharingStarted.Eagerly, 16)

    val fontWeight: StateFlow<FontWeight> = settingsDataStore.fontWeight
        .stateIn(viewModelScope, SharingStarted.Eagerly, FontWeight.Normal)

    // Hàm cập nhật cho từng cài đặt
    fun updateTheme(isDark: Boolean) {
        viewModelScope.launch {
            settingsDataStore.updateDarkTheme(isDark)
        }
    }

    fun updatePrimaryColor(color: Color) {
        viewModelScope.launch {
            settingsDataStore.updatePrimaryColor(color)
        }
    }

    fun updateFontSize(size: Int) {
        viewModelScope.launch {
            settingsDataStore.updateFontSize(size)
        }
    }

    fun updateFontWeight(weight: FontWeightOption) {
        viewModelScope.launch {
            settingsDataStore.updateFontWeight(weight)
        }
    }
}