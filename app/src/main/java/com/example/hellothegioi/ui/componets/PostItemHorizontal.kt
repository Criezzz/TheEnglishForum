package com.example.hellothegioi.ui.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.R
import com.example.hellothegioi.data.model.Post
import androidx.compose.foundation.clickable
import com. example. hellothegioi. data. repository. ExamplePost
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.layout.ContentScale
import java.util.concurrent.TimeUnit
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import com.example.hellothegioi.ui.theme.HellothegioiTheme
import androidx. compose. foundation. shape. RoundedCornerShape

@Composable
fun PostItemHorizontal(post: Post, modifier: Modifier = Modifier, onNavigateToComment: (Post) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        // Top Row: Avatar, Owner Name, and Date + More Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = post.avatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = post.ownerName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = if (post.isDraft) "Draft" else getTimeAgo(post.postTimeMillis),
                color = if (post.isDraft) Color.Red else Color.Gray,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = { post.onMore() },
                modifier = Modifier.offset(y = (-12).dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "More Options",
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = post.title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(modifier = Modifier.heightIn(max = 100.dp)) {
            Text(
                text = post.text,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = Int.MAX_VALUE,
                overflow = TextOverflow.Ellipsis
            )
            if (post.text.length > 100) {
                Text(
                    text = "Read More...",
                    fontSize = 12.sp,
                    color = Color.Blue,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        post.image?.let { imageRes ->
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Post Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Like - Comment - Share buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActionWithIcon(R.drawable.ic_heart, "${post.likes}", Color.Red) { post.onLike() }

            Spacer(modifier = Modifier.width(64.dp))

            ActionWithIcon(R.drawable.ic_comment, "${post.comments}", Color.Gray) {
                onNavigateToComment(post)
            }

            Spacer(modifier = Modifier.width(64.dp))

            ActionWithIcon(R.drawable.ic_share, "${post.shares}", Color.Gray) { post.onShare() }
        }
    }
}

@Composable
fun ActionWithIcon(iconRes: Int, count: String, tint: Color, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = tint
            )
        }
        Text(text = count, fontSize = 14.sp, color = Color.Gray)
    }
}

fun getTimeAgo(postTime: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - postTime
    val hours = TimeUnit.MILLISECONDS.toHours(diff)

    return when {
        hours < 1 -> "min"
        hours < 24 -> "$hours hour"
        else -> "${hours / 24} day"
    }
}

@Preview(showBackground = true)
@Composable
fun PostItemHorizontalPreview() {
    HellothegioiTheme {
        val examplePost = ExamplePost.getAll()[0]
        PostItemHorizontal(post = examplePost, onNavigateToComment = {})
    }
}
