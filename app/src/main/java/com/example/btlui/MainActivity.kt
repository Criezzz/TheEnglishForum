package com.example.btlui

import android.net.Uri
import android.os.Bundle
import androidx.compose.material3.*
import coil.compose.rememberAsyncImagePainter
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btlui.ui.theme.BTLUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTLUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Đăng ký",
            style = TextStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp,
                textAlign = TextAlign.End,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, end = 16.dp)
                .clickable { /*TODO*/ }
        )

        Text(
            text = "ENGF",
            style = TextStyle(
                color = Color.Blue,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )

        Text(
            text = "Đăng nhập",
            style = TextStyle(
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tên tài khoản:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Nhập tên tài khoản") },
                modifier = Modifier
                    .width(220.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mật khẩu:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Nhập mật khẩu") },
                modifier = Modifier
                    .width(220.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        ) {
            Text(text = "Đăng nhập", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Quên mật khẩu?",
                color = Color.Blue,
                modifier = Modifier.clickable { /*TODO*/ }
            )
            Text(
                text = " / ",
                modifier = Modifier.padding(horizontal = 0.dp)
            )
            Text(
                text = "Help",
                color = Color.Blue,
                modifier = Modifier.clickable { /*TODO*/ }
            )
        }
    }
}


@Composable
fun CreateScreen(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repassword by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("Giáo viên") }
    var isFocused by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "ENGF",
            style = TextStyle(
                color = Color.Blue,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        Text(
            text = "Đăng ký",
            style = TextStyle(
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tên tài khoản:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Nhập tên tài khoản") },
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tên người dùng:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Nhập tên của bạn") },
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mật khẩu:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Nhập mật khẩu") },
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Xác nhận mật khẩu:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = repassword,
                onValueChange = { repassword = it },
                placeholder = { Text("Xác nhận mật khẩu") },
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Email:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Nhập email") },
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Vai trò:",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedRole == "Giáo viên",
                onClick = { selectedRole = "Giáo viên" }
            )
            Text(text = "Giáo viên", modifier = Modifier.padding(end = 16.dp))

            RadioButton(
                selected = selectedRole == "Học sinh",
                onClick = { selectedRole = "Học sinh" }
            )
            Text(text = "Học sinh")
        }

        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        ) {
            Text(text = "Đăng ký", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Đã có tài khoản?",
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = "Đăng nhập",
                color = Color.Blue,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { /*TODO*/ }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bạn cần trợ giúp?",
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = "Help",
                color = Color.Blue,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { /*TODO*/ }
            )
        }
    }
}


@Composable
fun RePasswordScreen() {
    var password by remember { mutableStateOf("") }
    var repassword by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "ENGF",
            style = TextStyle(
                color = Color.Blue,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        Text(
            text = "Xác nhận mật khẩu",
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mật khẩu mới:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Nhập mật khẩu mới") },
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Xác nhận mật khẩu:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = repassword,
                onValueChange = { repassword = it },
                placeholder = { Text("Xác nhận mật khẩu") },
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        ) {
            Text(text = "Đặt lại mật khẩu", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bạn cần trợ giúp?",
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = "Help",
                color = Color.Blue,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { /*TODO*/ }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Trở lại đăng nhập?",
                color = Color.Blue,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { /*TODO*/ }
            )
        }
    }
}


@Composable
fun CFInstructor() {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "ENGF",
            style = TextStyle(
                color = Color.Blue,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Xác nhận tài khoản giáo viên",
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                        .clickable { launcher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Tải ảnh lên",
                        tint = Color.Blue,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Tải ảnh lên", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(18.dp))
                selectedImageUri?.let { uri ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentDescription = "Ảnh đã chọn",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        ) {
            Text(text = "Xác nhận", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Trở lại đăng nhập?",
                color = Color.Blue,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { /*TODO*/ }
            )
        }
    }
}


@Composable
fun ForgotPassword() {
    var username by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "ENGF",
            style = TextStyle(
                color = Color.Blue,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        Text(
            text = "Quên mật khẩu",
            style = TextStyle(
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tên tài khoản:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Nhập tên tài khoản") },
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tên người dùng:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Nhập tên của bạn") },
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Email:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Nhập email") },
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        ) {
            Text(text = "Xác nhận", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Trở lại đăng nhập?",
                color = Color.Blue,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { /*TODO*/ }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen() {
    val notifications = listOf(
        "Bạn có một bài tập mới.",
        "Lịch học ngày mai đã được cập nhật.",
        "Giảng viên đã chấm điểm bài kiểm tra.",
        "Thông báo nghỉ học đột xuất."
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Thông báo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3),
                    titleContentColor = Color.White
                )
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    contentPadding = paddingValues,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    itemsIndexed(notifications) { index, message -> // Dùng itemsIndexed() nếu cần index
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Text(
                                text = "$index: $message",
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
                Button(
                    onClick = { /* */ },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 20.dp, bottom = 16.dp)
                ) {
                    Text("Xác nhận")
                }

            }
        }
    )
}

@Composable
fun HelpScreen(onBackClick: () -> Unit = {}) {
    val questions = listOf(
        "Tôi quên tài khoản!" to "Để lấy lại tài khoản, vui lòng nhấn vào 'Quên mật khẩu' và làm theo hướng dẫn.",
        "Tôi không thể đăng nhập!" to "Kiểm tra lại tên đăng nhập và mật khẩu, hoặc thử đổi mật khẩu nếu bạn quên.",
        "Tôi muốn đăng ký!" to "Để đăng ký, hãy nhấn vào 'Đăng ký' trên màn hình đăng nhập.",
        "Tôi không thể đăng bài!" to "Vui lòng kiểm tra lại kết nối mạng và thử lại. Nếu vấn đề vẫn còn, liên hệ với hỗ trợ kỹ thuật."
    )

    var expandedIndex by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onBackClick() }
                    .padding(end = 20.dp, top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Trở lại đăng nhập?",
                    color = Color.Blue,
                    fontSize = 16.sp
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = innerPadding.calculateBottomPadding() + 60.dp // tránh che bởi bottomBar
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    text = "ENGF",
                    style = TextStyle(
                        color = Color.Blue,
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.Cursive,
                        letterSpacing = 2.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )
            }

            item {
                Text(
                    text = "Bạn cần hỗ trợ gì?",
                    style = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            itemsIndexed(questions) { index, question ->
                val isExpanded = expandedIndex == index
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .clickable {
                                expandedIndex = if (isExpanded) null else index
                            }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = question.first,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        if (isExpanded) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = question.second,
                                style = TextStyle(fontSize = 16.sp)
                            )
                        }
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BTLUITheme {
        HelpScreen()
    }
}