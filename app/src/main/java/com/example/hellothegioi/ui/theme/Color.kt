package com.example.hellothegioi.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Base Colors
val NavyBlue = Color(0xFF001F54)
val LightNavyBlue = Color(0xFF003F88)
val GrayBlue = Color(0xFF4E5D73)
val LightGrayBlue = Color(0xFF8A9BAE)
val AccentRed = Color(0xFFB23A48)
val LightAccentRed = Color(0xFFE57373)

// Danh sách các màu cho người dùng lựa chọn
val ThemeColorOptions = listOf(
    Color(0xFF001F54), // NavyBlue
    Color(0xFF003F88), // LightNavyBlue
    Color(0xFF4E5D73), // GrayBlue
    Color(0xFF8A9BAE), // LightGrayBlue
    Color(0xFFB23A48), // AccentRed
    Color(0xFF673AB7), // Deep Purple
    Color(0xFF2196F3), // Blue
    Color(0xFF4CAF50), // Green
    Color(0xFFFF9800)  // Orange
)

// Hàm tạo color scheme dựa trên màu chính
fun createDarkColorScheme(primaryColor: Color) = darkColorScheme(
    primary = primaryColor,
    secondary = GrayBlue,
    tertiary = AccentRed
)

fun createLightColorScheme(primaryColor: Color) = lightColorScheme(
    primary = primaryColor,
    secondary = LightGrayBlue,
    tertiary = LightAccentRed
)
val Blue = Color(0xFF2196F3)
val Green = Color(0xFF4CAF50)
val Purple = Color(0xFF9C27B0)
val Pink = Color(0xFFE91E63)
val Orange = Color(0xFFFF9800)
val NavyyBlue = Color(0xFF001F54)