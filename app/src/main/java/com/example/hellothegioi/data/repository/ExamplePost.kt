package com.example.hellothegioi.data.repository

import com.example.hellothegioi.R
import com.example.hellothegioi.data.model.Post

object ExamplePost{
    private val examplePosts = listOf(
        Post(
            avatar = R.drawable.ic_user_avatar,
            ownerName = "John Doe",
            postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 3,
            title = "Welcome to the Forum!",
            text = "This is our first post. Let's build a great community together!",
            image = R.drawable.image_test,
            isDraft = false,
            onClick = { println("Post 1 clicked") },
            onMore = { println("More options") },
            onLike = { println("Liked") },
            onComment = { println("Commented") },
            onShare = { println("Shared") }
        ),
        Post(
            avatar = R.drawable.ic_user_avatar,
            ownerName = "John Smith",
            postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 4,
            title = "Hello World!",
            text = "The First Post!",
            image = R.drawable.image_test,
            isDraft = false,
            onClick = { println("Post 1 clicked") },
            onMore = { println("More options") },
            onLike = { println("Liked") },
            onComment = { println("Commented") },
            onShare = { println("Shared") }
        )
    )

    fun getAll(): List<Post> = examplePosts
}
