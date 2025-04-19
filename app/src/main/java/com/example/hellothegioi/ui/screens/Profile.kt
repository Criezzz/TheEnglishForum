//package com.example.hellothegioi.ui.screens
//
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.hellothegioi.data.model.Post
//import com.example.hellothegioi.R
//import com.example.hellothegioi.ui.theme.HellothegioiTheme
//import com.example.hellothegioi.ui.theme.LightNavyBlue
//
//@Composable
//fun ProfileScreen(
//    name: String,
//    role: String,
//    follower: Int,
//    following: Int,
//    bio: String,
//    onEditProfile: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    var selectedSection by remember { mutableStateOf("Post") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(WindowInsets.statusBars.asPaddingValues())
//    ) {
//        // Header
//        Text(
//            text = "Profile",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.White,
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(LightNavyBlue)
//                .padding(vertical = 8.dp),
//            textAlign = TextAlign.Center
//        )
//
//        // Profile Info
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//                .border(BorderStroke(1.dp, LightNavyBlue), RoundedCornerShape(8.dp))
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Column(
//                    modifier = Modifier.weight(2f)
//                ) {
//                    Text(text = name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                    Text(text = "Role: $role", fontSize = 16.sp, color = Color.Gray)
//                    Text(text = "Followers: $follower", fontSize = 16.sp, color = Color.Gray)
//                    Text(text = "Following: $following", fontSize = 16.sp, color = Color.Gray)
//                    Text(text = bio, fontSize = 14.sp, color = Color.Gray)
//                }
//                Column(
//                    modifier = Modifier.weight(1f),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.chest),
//                        contentDescription = "Points Chest",
//                        modifier = Modifier.size(40.dp)
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Box {
//                        Image(
//                            painter = painterResource(id = R.drawable.profile_pic),
//                            contentDescription = "User Avatar",
//                            modifier = Modifier
//                                .size(80.dp)
//                                .clip(CircleShape)
//                        )
//                        Icon(
//                            painter = painterResource(id = R.drawable.gear),
//                            contentDescription = "Settings",
//                            modifier = Modifier
//                                .size(24.dp)
//                                .align(Alignment.TopEnd),
//                            tint = Color.Unspecified,
//                        )
//                    }
//                }
//            }
//        }
//
//        // Section Tabs
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//                .border(BorderStroke(1.dp, LightNavyBlue), RoundedCornerShape(8.dp)),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            listOf("Post", "Save", "Report", "Share").forEach { section ->
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                        .clickable { selectedSection = section }
//                        .background(
//                            if (selectedSection == section) LightNavyBlue else Color.Transparent,
//                            RoundedCornerShape(8.dp)
//                        )
//                        .padding(8.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = section,
//                        fontSize = 16.sp,
//                        fontWeight = if (selectedSection == section) FontWeight.Bold else FontWeight.Normal,
//                        color = if (selectedSection == section) Color.White else LightNavyBlue
//                    )
//                }
//            }
//        }
//
//        // Dynamic Content
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//                .padding(16.dp)
//        ) {
//            when (selectedSection) {
//                "Post" -> {
//                    val samplePosts = listOf(
//                        Post(
//                            avatar = R.drawable.profile_pic,
//                            ownerName = "John Doe (Me)",
//                            postDate = "2023-10-01",
//                            isDraft = false,
//                            title = "My First Post",
//                            text = "This is the content of my first post. It is a short description.",
//                            image = R.drawable.profile_pic,
//                            likes = 10,
//                            comments = 5,
//                            onClick = { /* Navigate to post UI */ }
//                        ),
//                        Post(
//                            avatar = R.drawable.profile_pic,
//                            ownerName = "Jane Smith",
//                            postDate = "2023-10-02",
//                            isDraft = true,
//                            title = "Draft Post",
//                            text = "This is a draft post. Only visible to the owner.",
//                            image = null,
//                            likes = 3,
//                            comments = 1,
//                            onClick = { /* Navigate to post UI */ }
//                        ),
//                        Post(
//                            avatar = R.drawable.profile_pic,
//                            ownerName = "Alice Johnson",
//                            postDate = "2023-09-30",
//                            isDraft = false,
//                            title = "Exploring Compose",
//                            text = "Compose is a modern toolkit for building native Android UI. It simplifies and accelerates UI development.",
//                            image = R.drawable.profile_pic,
//                            likes = 25,
//                            comments = 10,
//                            onClick = { /* Navigate to post UI */ }
//                        ),
//                        Post(
//                            avatar = R.drawable.profile_pic,
//                            ownerName = "Bob Brown",
//                            postDate = "2023-09-29",
//                            isDraft = false,
//                            title = "A Day in the Life",
//                            text = "Today was a great day! I went hiking in the mountains and enjoyed the beautiful scenery.",
//                            image = null,
//                            likes = 15,
//                            comments = 7,
//                            onClick = { /* Navigate to post UI */ }
//                        ),
//                        Post(
//                            avatar = R.drawable.profile_pic,
//                            ownerName = "Charlie Green",
//                            postDate = "2023-09-28",
//                            isDraft = true,
//                            title = "Upcoming Project",
//                            text = "I'm working on an exciting new project. Stay tuned for updates!",
//                            image = null,
//                            likes = 5,
//                            comments = 2,
//                            onClick = { /* Navigate to post UI */ }
//                        )
//                    ).sortedByDescending { it.isDraft }// Draft posts on top
//
//                    LazyColumn(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        items(samplePosts) { post ->
//                            PostItemHorizontal(post = post)
//                        }
//                    }
//                }
//                "Save" -> Text("Saved Posts")
//                "Report" -> Text("Reported Posts")
//                "Share" -> Text("Shared Posts")
//            }
//        }
//    }
//}
//
//@Composable
//fun PostItemHorizontal(post: Post, modifier: Modifier = Modifier) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable { post.onClick() },
//        shape = RoundedCornerShape(8.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            // Top Row: Avatar, Owner Name, and Draft/Date
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Image(
//                    painter = painterResource(id = post.avatar),
//                    contentDescription = "Avatar",
//                    modifier = Modifier
//                        .size(40.dp)
//                        .clip(CircleShape)
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = post.ownerName,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp
//                )
//                Spacer(modifier = Modifier.weight(1f))
//                Text(
//                    text = if (post.isDraft) "Draft" else post.postDate,
//                    color = if (post.isDraft) Color.Red else Color.Gray,
//                    fontSize = 12.sp
//                )
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Title
//            Text(
//                text = post.title,
//                fontWeight = FontWeight.Bold,
//                fontSize = 18.sp,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            // Post Content with Height Restriction
//            Box(modifier = Modifier.heightIn(max = 100.dp)) { // Restrict height
//                Text(
//                    text = post.text,
//                    fontSize = 14.sp,
//                    color = Color.Gray,
//                    maxLines = Int.MAX_VALUE,
//                    overflow = TextOverflow.Ellipsis
//                )
//                if (post.text.length > 100) { // Show "Read More" if content is long
//                    Text(
//                        text = "Read More...",
//                        fontSize = 12.sp,
//                        color = Color.Blue,
//                        modifier = Modifier.align(Alignment.BottomEnd)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Image or Image Count Icon
//            Box(modifier = Modifier.fillMaxWidth()) {
//                post.image?.let { imageRes ->
//                    Image(
//                        painter = painterResource(id = imageRes),
//                        contentDescription = "Post Image",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(150.dp),
//                        contentScale = ContentScale.Crop
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Heart and Comment Buttons
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.heart),
//                        contentDescription = "Like",
//                        modifier = Modifier.size(20.dp),
//                        tint = Color.Red
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Text(text = "${post.likes}", fontSize = 14.sp, color = Color.Gray)
//                }
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.cmt),
//                        contentDescription = "Comment",
//                        modifier = Modifier.size(20.dp),
//                        tint = Color.Gray
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Text(text = "${post.comments}", fontSize = 14.sp, color = Color.Gray)
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ProfileScreenPreview() {
//    HellothegioiTheme {
//        ProfileScreen(
//            name = "John Doe",
//            role = "Student",
//            follower = 1000,
//            following = 100,
//            bio = "This is a short bio about the user.",
//            onEditProfile = { /* Handle edit profile */ }
//        )
//    }
//}