package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.hellothegioi.data.model.Notification
import com.example.hellothegioi.data.repository.ExampleNotification
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.data.model.NotificationTarget

@Composable
fun NotificationScreen(onNavigateToComment : (Notification) -> Unit, onNavigateToQuestion : (Notification) -> Unit) {
    val notifications = ExampleNotification().notifications

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = "Thông báo",
            fontSize = 30.sp,
            color = Color.Black
        )
        Scaffold(
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
                        itemsIndexed(notifications) { _, notification ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .clickable {
                                            when (val target = notification.target) {
                                                is NotificationTarget.Post -> onNavigateToComment(notification)
                                                is NotificationTarget.ComposePage -> onNavigateToQuestion(notification)
                                            }
                                        }
                                    ,
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = notification.messenger,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = notification.time,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    }

                    Button(
                        onClick = { /* xử lý nút Xác nhận nếu cần */ },
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
}
