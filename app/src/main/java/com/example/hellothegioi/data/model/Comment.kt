package com.example.hellothegioi.data.model

data class Comment(
    val userName: String,
    val avatar: Int,
    val time: String,
    val text: String,
    val likes: Int = 0,
    val replies: Int = 0
)
