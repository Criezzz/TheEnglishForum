package com.example.hellothegioi.data.model

data class User(
    val name: String,
    val role: String,
    val follower: Int,
    val following: Int,
    val bio: String,
    val username: String,
    val age: String,
    val gender: String,
    val level: String,
    val email: String,
    val isVerifiedTeacher: Boolean,
    val password: String
)