package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.R
import androidx.compose.ui.tooling.preview.Preview
import com. example. hellothegioi. data. repository. ExamplePost
import com.example.hellothegioi.ui.componets.PostItemHorizontal

//import androidx. compose. foundation. lazy. LazyColumn
//import com. example. englishforum. ui. components. PostItemHorizontal
//import com. example. englishforum. data. model. Post

@Composable
fun HomeScreen() {
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

        Divider(color = Color.Gray, thickness = 1.dp)

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
                        BasicTextField(
                            value = text,
                            onValueChange = { text = it },
                            textStyle = TextStyle(fontSize = 18.sp, color = Color.Gray),
                            decorationBox = { innerTextField ->
                                if (text.isEmpty()) {
                                    Text("Write in....", color = Color.LightGray)
                                }
                                innerTextField()
                            }
                        )
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
                            contentDescription = "Attach File",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Divider(color = Color.Gray, thickness = 1.dp)
            }

            items(listpost) { post ->
                PostItemHorizontal(post = post)
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

