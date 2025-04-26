package com.example.qdaily

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: Int,
    val content: String,
    val hashtag: String,
    val date: String,
    val answers: List<String> = emptyList()
)

