package com.example.hellothegioi.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CFInstructor(onNavigateToLogin: () -> Unit) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

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
            headlineLarge = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            titleLarge = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            ),
            titleMedium = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            ),
            bodyLarge = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            labelLarge = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Xác nhận giáo viên",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.5.sp
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onNavigateToLogin) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                // Title
                Text(
                    text = "Xác nhận tài khoản giáo viên",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Text(
                    text = "Để đảm bảo chất lượng giảng dạy, vui lòng tải lên các giấy tờ xác minh của bạn.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Image Upload Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (selectedImageUri == null) {
                            Box(
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
                                    .clickable { launcher.launch("image/*") },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Tải ảnh lên",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(48.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Tải ảnh lên",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        selectedImageUri?.let { uri ->
                            Spacer(modifier = Modifier.height(16.dp))
                            Image(
                                painter = rememberAsyncImagePainter(uri),
                                contentDescription = "Ảnh đã chọn",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Submit button
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Xác nhận",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = {
                            Text(
                                text = "Xác nhận thông tin thành công",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        },
                        text = {
                            Text(
                                text = "Bạn đã xác nhận thông tin thành công. Vui lòng chờ phản hồi.\n" +
                                        "Bạn có thể đăng nhập với tư cách học viên để xem bài viết. Bạn có muốn trở lại đăng nhập không?",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    showDialog = false
                                    onNavigateToLogin()
                                }
                            ) {
                                Text(
                                    text = "OK",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = { showDialog = false }
                            ) {
                                Text(
                                    text = "Huỷ",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        properties = DialogProperties(dismissOnClickOutside = false)
                    )
                }
            }
        }
    }
}

