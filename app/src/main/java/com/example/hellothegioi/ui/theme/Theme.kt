package com.example.hellothegioi.ui.theme

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
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HellothegioiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    primaryColor: Color = if (darkTheme) NavyBlue else LightNavyBlue,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> createDarkColorScheme(primaryColor)
        else -> createLightColorScheme(primaryColor)
    }

    // Tạo Typography dựa trên font size và font weight
    val typography = createTypography(fontSize, fontWeight)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}