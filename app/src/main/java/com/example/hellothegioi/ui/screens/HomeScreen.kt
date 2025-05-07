package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.R
import com.example.hellothegioi.data.model.Post
import com.example.hellothegioi.data.repository.ExamplePost
import com.example.hellothegioi.ui.componets.PostItemHorizontal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToNewPost: () -> Unit, onNavigateToComment: (Post) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "The English Forum",
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp)
        ) {
            val listpost = ExamplePost.getAll()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
            ) {
                LazyColumn {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_user_avatar),
                                contentDescription = "User Avatar",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "NameUser",
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onNavigateToNewPost() }
                                        .padding(4.dp)
                                ) {
                                    Text(
                                        text = "Write in....",
                                        color = Color.LightGray,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 55.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            IconButton(onClick = { /* Handle photo */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_camera),
                                    contentDescription = "Add Photo",
                                    modifier = Modifier.size(25.dp)
                                )
                            }
                            IconButton(onClick = { /* Handle picture */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_picture),
                                    contentDescription = "Add Emoji",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            IconButton(onClick = { /* Handle add */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_add),
                                    contentDescription = "Add",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                    }

                    items(listpost) { post ->
                        PostItemHorizontal(
                            post = post,
                            onNavigateToComment = onNavigateToComment
                        )
                        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                    }
                }
            }
        }
    }
}