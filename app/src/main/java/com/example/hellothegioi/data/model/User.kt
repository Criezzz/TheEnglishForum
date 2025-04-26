package com.example.hellothegioi.data.model

data class User(
    val name: String,
    val role: String,
    val follower: Int,
    val following: Int,
    val bio: String
)