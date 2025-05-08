package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hellothegioi.ui.theme.FontWeightOption
import com.example.hellothegioi.ui.theme.ThemeColorOptions
import com.example.hellothegioi.ui.screens.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel
) {
    // Lấy giá trị hiện tại từ ViewModel
    val isDarkTheme by viewModel.isDarkTheme.collectAsState()
    val primaryColor by viewModel.primaryColor.collectAsState()
    val fontSize by viewModel.fontSize.collectAsState()
    val fontWeight by viewModel.fontWeight.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = primaryColor,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                modifier = Modifier.height(80.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Section: Theme
            SettingsSection(title = "Giao diện") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectableGroup(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ThemeOption(
                        text = "Sáng",
                        selected = !isDarkTheme,
                        onClick = { viewModel.updateTheme(false) }
                    )
                    ThemeOption(
                        text = "Tối",
                        selected = isDarkTheme,
                        onClick = { viewModel.updateTheme(true) }
                    )
                }
            }

            // Section: Color
            SettingsSection(title = "Màu chủ đạo") {
                ColorPicker(
                    colors = ThemeColorOptions,
                    selectedColor = primaryColor,
                    onColorSelected = { color ->
                        viewModel.updatePrimaryColor(color)
                    }
                )
            }

            // Section: Font Size
            SettingsSection(title = "Kích thước chữ") {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        "Kích thước hiện tại: $fontSize sp",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Slider(
                        value = fontSize.toFloat(),
                        onValueChange = { viewModel.updateFontSize(it.toInt()) },
                        valueRange = 12f..24f,
                        steps = 6,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("12", style = MaterialTheme.typography.bodySmall)
                        Text("24", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            // Section: Font Weight
            SettingsSection(title = "Độ đậm chữ") {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    FontWeightOption.values().forEach { option ->
                        FontWeightItem(
                            option = option,
                            selected = fontWeight == option.weight,
                            onClick = { viewModel.updateFontWeight(option) }
                        )
                    }
                }
            }

            // Preview Section
            SettingsSection(title = "Xem trước") {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Ví dụ tiêu đề",
                            style = MaterialTheme.typography.titleLarge,
                            color = primaryColor
                        )
                        Text(
                            text = "Đây là văn bản mẫu để xem trước cài đặt typography và màu sắc của bạn.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                        ) {
                            Text("Nút mẫu")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        content()
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun ThemeOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ColorPicker(
    colors: List<Color>,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        colors.forEach { color ->
            ColorCircle(
                color = color,
                selected = color == selectedColor,
                onClick = { onColorSelected(color) }
            )
        }
    }
}

@Composable
fun ColorCircle(
    color: Color,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(color)
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.8f))
            )
        }
    }
}

@Composable
fun FontWeightItem(
    option: FontWeightOption,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = option.displayName,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = option.weight)
        )
    }
}