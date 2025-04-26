package com.example.qdaily

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Lớp quản lý theme cho toàn bộ ứng dụng
 */
class AppThemeManager {
    // Các tùy chọn cài đặt giao diện
    val themeOptions = listOf("Hệ thống", "Sáng", "Tối")
    val colorOptions = listOf("Xanh Dương", "Xanh Lá", "Tím", "Hồng", "Cam")
    val fontSizeOptions = listOf("Nhỏ", "Vừa", "To")
    val fontStyleOptions = listOf("Sans Serif", "Serif", "Mono", "Rounded")
    val layoutOptions = listOf("Thoáng", "Mặc định", "Nén")

    // State mặc định
    private val _themeMode = mutableStateOf("Hệ thống")
    private val _accentColor = mutableStateOf("Xanh Dương")
    private val _fontSize = mutableStateOf("Vừa")
    private val _fontStyle = mutableStateOf("Sans Serif")
    private val _layoutDensity = mutableStateOf("Mặc định")
    private val _animationsEnabled = mutableStateOf(true)

    // Các getter và setter để truy cập và thay đổi state
    var themeMode: String
        get() = _themeMode.value
        set(value) { _themeMode.value = value }

    var accentColor: String
        get() = _accentColor.value
        set(value) { _accentColor.value = value }

    var fontSize: String
        get() = _fontSize.value
        set(value) { _fontSize.value = value }

    var fontStyle: String
        get() = _fontStyle.value
        set(value) { _fontStyle.value = value }

    var layoutDensity: String
        get() = _layoutDensity.value
        set(value) { _layoutDensity.value = value }

    var animationsEnabled: Boolean
        get() = _animationsEnabled.value
        set(value) { _animationsEnabled.value = value }

    // Đối tượng singleton
    companion object {
        private var instance: AppThemeManager? = null

        fun getInstance(): AppThemeManager {
            if (instance == null) {
                instance = AppThemeManager()
            }
            return instance!!
        }
    }

    // Các hàm tiện ích cho theme
    @Composable
    fun getBackgroundColor(): Color {
        return when (accentColor) {
            "Xanh Dương" -> Color.Blue
            "Xanh Lá" -> Color.Green
            "Tím" -> Color(0xFF9C27B0)
            "Hồng" -> Color.Magenta
            "Cam" -> Color(0xFFFF9800)
            else -> Color.Green
        }
    }

    @Composable
    fun getFontSizeSp(): androidx.compose.ui.unit.TextUnit {
        return when (fontSize) {
            "Nhỏ" -> 12.sp
            "Vừa" -> 16.sp
            "To" -> 20.sp
            else -> 16.sp
        }
    }

    @Composable
    fun getFontFamily(): FontFamily {
        return when (fontStyle) {
            "Sans Serif" -> FontFamily.SansSerif
            "Serif" -> FontFamily.Serif
            "Mono" -> FontFamily.Monospace
            "Rounded" -> FontFamily.Cursive
            else -> FontFamily.SansSerif
        }
    }

    @Composable
    fun getCustomTypography(): Typography {
        val fontFamily = getFontFamily()

        return Typography(
            displayLarge = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            ),
            displayMedium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            displaySmall = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            ),
            headlineLarge = TextStyle(fontFamily = fontFamily, fontSize = 18.sp),
            headlineMedium = TextStyle(fontFamily = fontFamily, fontSize = 16.sp),
            headlineSmall = TextStyle(fontFamily = fontFamily, fontSize = 14.sp),
            bodyLarge = TextStyle(fontFamily = fontFamily, fontSize = 16.sp),
            bodyMedium = TextStyle(fontFamily = fontFamily, fontSize = 14.sp),
            bodySmall = TextStyle(fontFamily = fontFamily, fontSize = 12.sp),
            labelLarge = TextStyle(fontFamily = fontFamily, fontSize = 14.sp),
            labelMedium = TextStyle(fontFamily = fontFamily, fontSize = 12.sp),
            labelSmall = TextStyle(fontFamily = fontFamily, fontSize = 10.sp)
        )
    }

    @Composable
    fun getShapes(): Shapes {
        return Shapes(
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(8.dp),
            large = RoundedCornerShape(16.dp)
        )
    }

    @Composable
    fun getColorScheme(darkTheme: Boolean = isSystemInDarkTheme()): ColorScheme {
        return when (themeMode) {
            "Sáng" -> lightColorScheme()
            "Tối" -> darkColorScheme()
            else -> if (darkTheme) darkColorScheme() else lightColorScheme()
        }
    }

    @Composable
    fun isColorDark(color: Color): Boolean {
        val luminance = (0.299 * color.red + 0.587 * color.green + 0.114 * color.blue)
        return luminance < 0.5
    }

    // Lưu cài đặt của người dùng
    fun saveSettings() {
        // TODO: Implement lưu cài đặt vào SharedPreferences hoặc DataStore
    }

    // Tải cài đặt của người dùng khi khởi động app
    fun loadSettings() {
        // TODO: Implement tải cài đặt từ SharedPreferences hoặc DataStore
    }
}

/**
 * Composable chính để cung cấp theme cho toàn bộ ứng dụng
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val themeManager = remember { AppThemeManager.getInstance() }

    MaterialTheme(
        colorScheme = themeManager.getColorScheme(darkTheme),
        typography = themeManager.getCustomTypography(),
        shapes = themeManager.getShapes(),
        content = content
    )
}
