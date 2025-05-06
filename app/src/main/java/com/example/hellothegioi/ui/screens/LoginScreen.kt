package com.example.hellothegioi.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.example.hellothegioi.ui.theme.Montserrat
import com.example.hellothegioi.ui.theme.Bungee
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.data.repository.ExampleUser
import androidx.compose.ui.text.font.Font

import com.example.hellothegioi.R
import com.example.hellothegioi.ui.theme.NavyBlue

@Composable
fun LoginScreen(onNavigateToCreate : () -> Unit,
                onNavigateToHome : () -> Unit,
                onNavigateToHelp : () -> Unit,
                onNavigateToFogotPass : () -> Unit) {
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
                .clickable { onNavigateToCreate() },
        )

        Text(
            text = "The English Forum",
            style = TextStyle(
                color = NavyBlue,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = Montserrat,
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
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = Montserrat,
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

        Spacer(modifier = Modifier.height(20.dp))

        var errorMessage by remember { mutableStateOf("") }

        Button(
            onClick = {
                if (username.isEmpty() || password.isEmpty()) {
                    errorMessage = "Tên tài khoản hoặc mật khẩu không được để trống"
                } else if (username == ExampleUser.student.username && password == ExampleUser.student.password) {
                    onNavigateToHome()
                    println("Đăng nhập thành công với tài khoản: $username")
                } else if (username == ExampleUser.teacher.username && password == ExampleUser.teacher.password) {
                    onNavigateToHome()
                    println("Đăng nhập thành công với tài khoản: $username")
                } else {
                    errorMessage = "Tên tài khoản hoặc mật khẩu không đúng"
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        ) {
            Text(text = "Đăng nhập", fontSize = 16.sp)
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
                text = "Quên mật khẩu?",
                color = Color.Blue,
                modifier = Modifier.clickable { onNavigateToFogotPass() }
            )
            Text(
                text = " / ",
                modifier = Modifier.padding(horizontal = 0.dp)
            )
            Text(
                text = "Help",
                color = Color.Blue,
                modifier = Modifier.clickable { onNavigateToHelp() }
            )
        }
    }
}
