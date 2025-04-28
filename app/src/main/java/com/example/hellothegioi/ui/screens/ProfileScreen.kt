package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.R
import com.example.hellothegioi.data.model.Post
import com.example.hellothegioi.data.model.User
import com.example.hellothegioi.data.repository.ExamplePost
import com.example.hellothegioi.ui.componets.PostItemHorizontal
import com.example.hellothegioi.ui.theme.HellothegioiTheme
import com.example.hellothegioi.ui.theme.LightNavyBlue

@Composable
fun ProfileScreen(
    user: User,
    onEditProfile: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToComment: (Post) -> Unit = {}
) {
    var selectedSection by remember { mutableStateOf("Post") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        // Header
        Text(
            text = "Forum",
            fontSize = 20.sp,
            color = Color.Black
        )

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
                    val samplePosts = ExamplePost.getAll()
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

                "Save" -> Text("Saved Posts")
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
                    Icon(
                        painter = painterResource(id = R.drawable.gear),
                        contentDescription = "Settings",
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.TopEnd),
                        tint = Color.Unspecified,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    HellothegioiTheme {
        ProfileScreen(
            user = User(
                name = "John Doe",
                role = "Student",
                follower = 1000,
                following = 100,
                bio = "This is a short bio about the user."
            ),
            onEditProfile = { /* Handle edit profile */ }
        )
    }
}