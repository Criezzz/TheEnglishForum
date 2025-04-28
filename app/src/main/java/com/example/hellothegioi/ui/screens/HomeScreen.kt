package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.hellothegioi.data.repository.ExamplePost
import com.example.hellothegioi.ui.componets.PostItemHorizontal
import androidx.compose.foundation.clickable
import com.example.hellothegioi.data.model.Post

@Composable
fun HomeScreen(onNavigateToNewPost: () -> Unit, onNavigateToComment: (Post) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = "The English Forum",
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        HorizontalDivider(thickness = 1.dp, color = Color.Gray)

        val listpost = ExamplePost.getAll()

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
                        Text(text = "NameUser", style = MaterialTheme.typography.bodyLarge)

                        Spacer(modifier = Modifier.height(5.dp))

                        var text by remember { mutableStateOf("") }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onNavigateToNewPost() }  // click
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

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen{}
//}

