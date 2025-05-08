package com.example.hellothegioi.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF90CAF9),
    onPrimary = Color.White,
    secondary = Color(0xFF81D4FA),
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color(0xFFE1E1E1),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE1E1E1),
    surfaceVariant = Color(0xFF2D2D2D),
    onSurfaceVariant = Color(0xFFBDBDBD)
)

private val LightColorScheme = lightColorScheme(
    primary = LightNavyBlue,
    onPrimary = Color.White,
    primaryContainer = LightNavyBlue.copy(alpha = 0.12f),
    onPrimaryContainer = LightNavyBlue,
    secondary = LightNavyBlue.copy(alpha = 0.7f),
    onSecondary = Color.White,
    secondaryContainer = LightNavyBlue.copy(alpha = 0.12f),
    onSecondaryContainer = LightNavyBlue,
    tertiary = LightNavyBlue.copy(alpha = 0.5f),
    onTertiary = Color.White,
    tertiaryContainer = LightNavyBlue.copy(alpha = 0.12f),
    onTertiaryContainer = LightNavyBlue,
    error = Color(0xFFB00020),
    onError = Color.White,
    errorContainer = Color(0xFFB00020).copy(alpha = 0.12f),
    onErrorContainer = Color(0xFFB00020),
    background = Color(0xFFFFF8E1), // Warm yellowish background
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFF8E1), // Warm yellowish surface
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFF5F0E0), // Slightly darker warm variant
    onSurfaceVariant = Color(0xFF49454F),
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0),
    scrim = Color.Black.copy(alpha = 0.32f),
    inverseSurface = Color(0xFF313033),
    inverseOnSurface = Color(0xFFF4EFF4),
    inversePrimary = LightNavyBlue.copy(alpha = 0.8f),
    surfaceTint = LightNavyBlue.copy(alpha = 0.05f)
)

@Composable
fun HellothegioiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    primaryColor: Color = if (darkTheme) Color(0xFF90CAF9) else Color(0xFF1976D2),
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme.copy(primary = primaryColor)
        else -> LightColorScheme.copy(primary = primaryColor)
    }

    val typography = createTypography(fontSize, fontWeight)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}

@Composable
fun LoginScreenTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF1976D2),
            onPrimary = Color.White,
            background = Color.White,
            onBackground = Color.Black,
            surface = Color.White,
            onSurface = Color.Black,
            error = Color(0xFFB00020),
            onError = Color.White
        ),
        typography = Typography(
            bodyLarge = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            bodyMedium = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            ),
            titleLarge = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        ),
        content = content
    )
}


