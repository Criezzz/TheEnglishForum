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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.hellothegioi.data.model.CurrentUser
import com.example.hellothegioi.data.model.Post
import com.example.hellothegioi.data.model.User
import com.example.hellothegioi.data.repository.ExamplePost
import com.example.hellothegioi.ui.componets.PostItemHorizontal
import com.example.hellothegioi.data.repository.ExampleUser
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateToComment: (Post) -> Unit = {},
    onNavigateToProfileSetting: () -> Unit,
    onNavigateToUISetting: () -> Unit
) {
    var selectedSection by remember { mutableStateOf("Post") }
    var showMenu by remember { mutableStateOf(false) }
    val currentUser = CurrentUser.user ?: ExampleUser.student

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    )
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.gear),
                            contentDescription = "Settings",
                            tint = Color.Unspecified
                        )
                    }

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("UI Settings") },
                            onClick = {
                                showMenu = false
                                onNavigateToUISetting()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Profile Settings") },
                            onClick = {
                                showMenu = false
                                onNavigateToProfileSetting()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Log Out") },
                            onClick = {
                                showMenu = false
                                println("User logged out")
                            }
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .fillMaxWidth()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp)
        ) {
            ProfileInfo(user = currentUser)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf("Posts", "Saved", "Reports", "Shared").forEach { section ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedSection = section }
                                .background(
                                    if (selectedSection == section) MaterialTheme.colorScheme.primary else Color.Transparent,
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = section,
                                fontSize = 16.sp,
                                fontWeight = if (selectedSection == section) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedSection == section) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp)
            ) {
                when (selectedSection) {
                    "Posts" -> {
                        val samplePosts = ExamplePost.getUserPost()
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(samplePosts) { post ->
                                PostItemHorizontal(post = post, onNavigateToComment = onNavigateToComment)
                                Divider(color = MaterialTheme.colorScheme.outline)
                            }
                        }
                    }
                    "Saved" -> {
                        val samplePost = ExamplePost.getAll().get(3)
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(listOf(samplePost)) { post ->
                                PostItemHorizontal(post = post, onNavigateToComment = onNavigateToComment)
                                Divider(color = MaterialTheme.colorScheme.outline)
                            }
                        }
                    }
                    "Reports" -> Text("Reported Posts")
                    "Shared" -> Text("Shared Posts")
                }
            }
        }
    }
}

@Composable
fun ProfileInfo(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Role: ${user.role}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Followers: ${user.follower}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Following: ${user.following}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = user.bio,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chest),
                    contentDescription = "Points Chest",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.profile_pic),
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}