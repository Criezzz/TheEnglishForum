package com.example.hellothegioi.data.model

data class Post(
    val avatar: Int,
    val ownerName: String,
    val postTimeMillis: Long = 0,
    val title: String,
    val text: String,
    val image: Int? = null,
    val isDraft: Boolean = false,
    val likes: Int = 0,
    val comments: Int = 0,
    val shares: Int = 0,
    val onClick: () -> Unit = {},
    val onMore: () -> Unit = {},
    val onLike: () -> Unit = {},
    val onComment: () -> Unit = {},
    val onShare: () -> Unit = {}
)