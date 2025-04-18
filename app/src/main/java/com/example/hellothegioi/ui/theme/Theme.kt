package com.example.hellothegioi.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val NavyBlue = Color(0xFF001F54)
val LightNavyBlue = Color(0xFF003F88)
val GrayBlue = Color(0xFF4E5D73)
val LightGrayBlue = Color(0xFF8A9BAE)
val AccentRed = Color(0xFFB23A48)
val LightAccentRed = Color(0xFFE57373)

private val DarkColorScheme = darkColorScheme(
    primary = NavyBlue,
    secondary = GrayBlue,
    tertiary = AccentRed
)

private val LightColorScheme = lightColorScheme(
    primary = LightNavyBlue,
    secondary = LightGrayBlue,
    tertiary = LightAccentRed
)

@Composable
fun HellothegioiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}