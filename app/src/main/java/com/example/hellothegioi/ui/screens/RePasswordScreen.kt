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
fun RePasswordScreen(onNavigateToHelp : () -> Unit,
                     onNavigateToLogin : () -> Unit) {
    var password by remember { mutableStateOf("") }
    var repassword by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

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
            onClick = {
                if(password.isEmpty() && repassword.isEmpty()) {
                    errorMessage = "vui lòng nhập mật khẩu mới"
                } else if(password != repassword) {
                    errorMessage = "Mật khẩu và xác nhận mật khẩu không khớp"
                } else {
                    showDialog = true
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        ) {
            Text(text = "Đặt lại mật khẩu", fontSize = 16.sp)
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Đổi mật khẩu thành công") },
                text = { Text("Bạn có chắc chắn muốn trở lại đăng nhập không?") },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false;
                        onNavigateToLogin()
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog = false
                    }) {
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