package com.example.hellothegioi.data.model

import java.util.*

data class Question(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val Q: Int, // Ngày trong tuần (1-7)
    var isAnswered: Boolean = false,
    var userSelectedAnswer: Int? = null
)

data class WeeklyQuestions(
    val week: Int,
    val questions: List<Question>
)