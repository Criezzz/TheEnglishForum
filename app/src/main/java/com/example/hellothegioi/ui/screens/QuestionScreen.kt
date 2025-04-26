package com.example.hellothegioi.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit. *

@Composable
fun QuestionScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Câu hỏi", fontSize = 24.sp)
        // Nội dung khác...
    }
}
