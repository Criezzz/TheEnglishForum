package com.example.hellothegioi.ui.screens

import androidx. compose. runtime. Composable
import androidx. compose. ui. unit. *
import androidx. compose. ui. Modifier
import androidx. compose. foundation. layout. Column
import androidx. compose. foundation. layout. *
import androidx. compose. material3.*

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Cá nhân", fontSize = 24.sp)
        // Nội dung khác...
    }
}
