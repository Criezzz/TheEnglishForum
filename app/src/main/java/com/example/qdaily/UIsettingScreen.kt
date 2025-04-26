package com.example.qdaily

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
 * Màn hình cài đặt giao diện người dùng
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UISettingsScreen() {
    val themeManager = AppThemeManager.getInstance()

    // Sử dụng các giá trị từ manager
    var themeMode by remember { mutableStateOf(themeManager.themeMode) }
    var accentColor by remember { mutableStateOf(themeManager.accentColor) }
    var fontSize by remember { mutableStateOf(themeManager.fontSize) }
    var fontStyle by remember { mutableStateOf(themeManager.fontStyle) }
    var layoutDensity by remember { mutableStateOf(themeManager.layoutDensity) }
    var animationsEnabled by remember { mutableStateOf(themeManager.animationsEnabled) }

    // Apply theme
    AppTheme {
        val backgroundColor = themeManager.getBackgroundColor()
        val fontSizeSp = themeManager.getFontSizeSp()
        val textColor = if (themeManager.isColorDark(backgroundColor)) Color.White else Color.Black

        // Sử dụng Surface để bao bọc toàn bộ màn hình với màu nền
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Cài đặt Giao Diện") },
                        navigationIcon = {
                            IconButton(onClick = { /* Quay lại */ }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = backgroundColor.copy(alpha = 0.9f) // Làm cho TopBar hơi trong suốt
                        )
                    )
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        "Tuỳ chỉnh giao diện hiển thị",
                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = fontSizeSp),
                        color = textColor
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    ExposedDropdownField("Chế độ Giao Diện", themeMode, themeManager.themeOptions) {
                        themeMode = it
                        themeManager.themeMode = it
                    }

                    ExposedDropdownField("Màu chủ đề", accentColor, themeManager.colorOptions) {
                        accentColor = it
                        themeManager.accentColor = it
                    }

                    ExposedDropdownField("Cỡ chữ", fontSize, themeManager.fontSizeOptions) {
                        fontSize = it
                        themeManager.fontSize = it
                    }

                    ExposedDropdownField("Kiểu chữ", fontStyle, themeManager.fontStyleOptions) {
                        fontStyle = it
                        themeManager.fontStyle = it
                    }

                    ExposedDropdownField("Độ nén bố cục", layoutDensity, themeManager.layoutOptions) {
                        layoutDensity = it
                        themeManager.layoutDensity = it
                    }

                    Button(
                        onClick = {
                            // Lưu cài đặt UI
                            themeManager.saveSettings()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (themeManager.isColorDark(backgroundColor))
                                Color.White.copy(alpha = 0.9f)
                            else
                                Color.Black.copy(alpha = 0.8f)
                        )
                    ) {
                        Text(
                            "Lưu Cài Đặt",
                            color = if (themeManager.isColorDark(backgroundColor)) Color.Black else Color.White
                        )
                    }
                }
            }
        }
    }
}