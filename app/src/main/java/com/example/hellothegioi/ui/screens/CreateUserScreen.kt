package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@Composable
fun CreateScreen(onNavigateTologin : () -> Unit, onNavigateToInstructor : () -> Unit, onNavigateToHelp : () -> Unit) {
    var username by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repassword by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("Giáo viên") }
    var isFocused by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
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
            onClick = {
                if (username.isBlank() || name.isBlank() || password.isBlank() || repassword.isBlank() || email.isBlank()) {
                    errorMessage = ("Vui lòng điền đầy đủ thông tin.")
                } else if (password != repassword) {
                    errorMessage = ("Mật khẩu và xác nhận mật khẩu không khớp.")
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    errorMessage = ("Email không hợp lệ.")
                } else if (selectedRole == "Giáo viên") {
                    onNavigateToInstructor()
                } else {
                    showDialog = true
                }
                      },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        ) {
            Text(text = "Đăng ký", fontSize = 16.sp)
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(
                        text = "Đăng ký thành công",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                text = {
                    Text(
                        text = "Bạn có chắc chắn muốn trở lại đăng nhập không?",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                            onNavigateTologin()
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("Huỷ")
                    }
                },
                properties = DialogProperties(dismissOnClickOutside = false)
            )
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )
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
                    .clickable { onNavigateTologin() }
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
                    .clickable { onNavigateToHelp() }
            )
        }
    }
}

