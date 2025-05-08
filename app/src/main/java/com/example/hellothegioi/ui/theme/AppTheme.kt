package com.example.hellothegioi.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.hellothegioi.ui.screens.SettingsViewModel

@Composable
fun AppTheme(
    settingsViewModel: SettingsViewModel,
    content: @Composable () -> Unit
) {
    // Collect theme settings from ViewModel
    val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState(initial = false)
    val primaryColor by settingsViewModel.primaryColor.collectAsState(initial = Color(0xFF1976D2))
    val fontSize by settingsViewModel.fontSize.collectAsState(initial = 16)
    val fontWeight by settingsViewModel.fontWeight.collectAsState(initial = FontWeight.Normal)

    // Create color scheme based on current theme
    val colorScheme = if (isDarkTheme) {
        darkColorScheme(
            primary = primaryColor,
            onPrimary = Color.White,
            secondary = primaryColor.copy(alpha = 0.7f),
            onSecondary = Color.White,
            background = Color(0xFF121212),
            onBackground = Color(0xFFE1E1E1),
            surface = Color(0xFF1E1E1E),
            onSurface = Color(0xFFE1E1E1),
            surfaceVariant = Color(0xFF2D2D2D),
            onSurfaceVariant = Color(0xFFBDBDBD),
            error = Color(0xFFCF6679),
            onError = Color.White
        )
    } else {
        lightColorScheme(
            primary = primaryColor,
            onPrimary = Color.White,
            secondary = primaryColor.copy(alpha = 0.7f),
            onSecondary = Color.White,
            background = Color.White,
            onBackground = Color.Black,
            surface = Color.White,
            onSurface = Color.Black,
            surfaceVariant = Color(0xFFF5F5F5),
            onSurfaceVariant = Color(0xFF666666),
            error = Color(0xFFB00020),
            onError = Color.White
        )
    }

    // Create typography based on current settings
    val typography = createTypography(fontSize, fontWeight)

    // Apply theme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
} 