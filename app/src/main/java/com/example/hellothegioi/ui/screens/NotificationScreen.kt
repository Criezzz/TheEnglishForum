package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.hellothegioi.data.model.Notification
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hellothegioi.data.model.NotificationTarget
import com.example.hellothegioi.data.repository.ExampleNotification

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    onNavigateToComment: (Notification) -> Unit,
    onNavigateToQuestion: () -> Unit
) {
    val exampleNotification = remember { ExampleNotification() }
    val notifications = exampleNotification.notificationsList

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Thông báo",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
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
                                    when (notification.target) {
                                        is NotificationTarget.Post -> onNavigateToComment(notification)
                                        is NotificationTarget.ComposePage -> onNavigateToQuestion()
                                    }
                                },
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = notification.messenger)
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
                    onClick = {  },
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
