package com.example.hellothegioi.ui.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.R
import com.example.hellothegioi.data.model.Comment
import com.example.hellothegioi.data.repository.ExampleComment

@Composable
fun CommentItem(comment: Comment) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Image(
                painter = painterResource(id = comment.avatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = comment.userName, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = comment.time, color = Color.Gray, fontSize = 12.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(44.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Text(text = comment.text, fontSize = 14.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding((30).dp, 0.dp, 0.dp, 0.dp)
        ) {
            ActionWithIcon(R.drawable.ic_heart, "${comment.likes}", Color.Red) { /* handle like */ }
            Spacer(modifier = Modifier.width(32.dp))
            ActionWithIcon(R.drawable.ic_comment, "${comment.replies}", Color.Gray) { /* handle reply */ }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommentItemPreview(){
    val cm = ExampleComment.getAll().get(0)
    CommentItem(cm)
}
