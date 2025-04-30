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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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

@Composable
fun ForgotPassword(onNavigateToLogin: () -> Unit,
                   onNavigateToRepass: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
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
            onClick = {
                if (username.isEmpty() || name.isEmpty() || email.isEmpty()) {
                    errorMessage = "Vui lòng điền đầy đủ thông tin"
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    errorMessage = "Email không hợp lệ"
                } else {
                    onNavigateToRepass()
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        ) {
            Text(text = "Xác nhận", fontSize = 16.sp)
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
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Trở lại đăng nhập?",
                color = Color.Blue,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { onNavigateToLogin() }
            )
        }
    }
}