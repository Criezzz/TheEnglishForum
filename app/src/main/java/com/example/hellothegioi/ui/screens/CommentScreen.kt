package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.data.model.*
import com.example.hellothegioi.data.repository.ExampleComment
import com.example.hellothegioi.data.repository.ExamplePost
import com.example.hellothegioi.ui.componets.*
import com.example.hellothegioi.R

@Composable
fun CommentScreen(post: Post, onBack:()->Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        // 1. Header post
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(25.dp)
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = "The English Forum",
                fontSize = 20.sp,
                color = Color.Black
            )
        }

        HorizontalDivider(thickness = 1.dp, color = Color.Gray)

        PostItemHorizontal(post = post, onNavigateToComment = {})

        HorizontalDivider(thickness = 1.dp, color = Color.Gray)

        // 2. Input comment
        CommentInput(onSend = { /* Xử lý gửi bình luận */ })

        HorizontalDivider(thickness = 1.dp, color = Color.Gray)

        // 3. List comment
        val listComment = ExampleComment.getAll()
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 8.dp)
        ) {
            items(listComment) { comment ->
                CommentItem(comment)
                HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentInput(onSend: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(16.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Comment..") },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            maxLines = 3,
            singleLine = false
        )

        IconButton(onClick = {
            if (text.isNotBlank()) {
                onSend(text)
                text = ""
            }
        }) {
            Icon(painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "Send",
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommentScreen() {
    val post = ExamplePost.getAll().get(1)
    CommentScreen(
        post = post,
        onBack = {}
    )
}


