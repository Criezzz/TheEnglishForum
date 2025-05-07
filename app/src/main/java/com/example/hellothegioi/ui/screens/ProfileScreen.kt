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
import com.example.hellothegioi.data.model.User
import com.example.hellothegioi.data.repository.ExamplePost
import com.example.hellothegioi.ui.componets.PostItemHorizontal
import com.example.hellothegioi.ui.theme.LightNavyBlue

@Composable
fun ProfileScreen(
    user: User,
    modifier: Modifier = Modifier,
    onNavigateToComment: (Post) -> Unit = {},
    onNavigateToProfileSetting: () -> Unit, // Add navigation callback for UserProfileScreen
    onNavigateToUISetting: () -> Unit
) {
    var selectedSection by remember { mutableStateOf("Post") }
    var showMenu by remember { mutableStateOf(false) } // State to control the dropdown menu

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Forum",
                fontSize = 20.sp,
                color = Color.Black
            )

            // Gear Icon with Dropdown Menu
            Box {
                Icon(
                    painter = painterResource(id = R.drawable.gear),
                    contentDescription = "Settings",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(36.dp)
                        .clickable { showMenu = true }
                )

                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("UI Setting") },
                        onClick = {
                            showMenu = false
                            // Handle UI Setting action here
                            onNavigateToUISetting()

                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Profile Setting") },
                        onClick = {
                            showMenu = false
                            onNavigateToProfileSetting() // Navigate to UserProfileScreen
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Log Out") },
                        onClick = {
                            showMenu = false
                            //mock the log out.
                            println("User logged out")
                        }
                    )
                }
            }
        }

        // Profile Info
        ProfileInfo(user = user)

        // Section Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(BorderStroke(1.dp, LightNavyBlue), RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Post", "Save", "Report", "Share").forEach { section ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedSection = section }
                        .background(
                            if (selectedSection == section) LightNavyBlue else Color.Transparent,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = section,
                        fontSize = 16.sp,
                        fontWeight = if (selectedSection == section) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedSection == section) Color.White else LightNavyBlue
                    )
                }
            }
        }

        // Dynamic Content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp) // Match the padding of the section tabs
                .border(BorderStroke(1.dp, LightNavyBlue), RoundedCornerShape(8.dp)) // Add border
                .padding(16.dp) // Inner padding
        ) {
            when (selectedSection) {
                "Post" -> {
                    val samplePosts = ExamplePost.getUserPost()
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(samplePosts) { post ->
                            PostItemHorizontal(post = post, onNavigateToComment = onNavigateToComment)
                            HorizontalDivider(thickness = 1.dp, color = Color.Gray) // Add splitter
                        }
                    }
                }
                //khi đổi tên ng dùng thì các post cũngddc dổi tên

                "Save" -> {
                    val samplePost = ExamplePost.getAll().get(3)
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(listOf(samplePost)) { post ->
                            PostItemHorizontal(post = post, onNavigateToComment = onNavigateToComment)
                            HorizontalDivider(thickness = 1.dp, color = Color.Gray) // Add splitter
                        }
                    }
                }
                "Report" -> Text("Reported Posts")
                "Share" -> Text("Shared Posts")
            }
        }
    }
}

@Composable
fun ProfileInfo(user: User) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(BorderStroke(1.dp, LightNavyBlue), RoundedCornerShape(8.dp))
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
                Text(text = user.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Role: ${user.role}", fontSize = 16.sp, color = Color.Gray)
                Text(text = "Followers: ${user.follower}", fontSize = 16.sp, color = Color.Gray)
                Text(text = "Following: ${user.following}", fontSize = 16.sp, color = Color.Gray)
                Text(text = user.bio, fontSize = 14.sp, color = Color.Gray)
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