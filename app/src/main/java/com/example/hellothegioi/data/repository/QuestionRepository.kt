package com.example.hellothegioi.data.repository

import com.example.hellothegioi.data.model.Question
import com.example.hellothegioi.data.model.WeeklyQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class QuestionRepository {
    // Dữ liệu mẫu
    private val sampleQuestions = listOf(
        Question(
            content = "What's the correct meaning of 'endeavor'?",
            options = listOf("To try hard", "To give up", "To succeed", "To fail"),
            correctAnswerIndex = 0,
            Q = 1
        ),
        Question(
            content = "Which sentence uses the present perfect tense correctly?",
            options = listOf(
                "I am living here since 2010.",
                "I have lived here since 2010.",
                "I live here since 2010.",
                "I will live here since 2010."
            ),
            correctAnswerIndex = 1,
            Q = 2
        ),
        Question(
            content = "What's the antonym of 'benevolent'?",
            options = listOf("Kind", "Malevolent", "Generous", "Charitable"),
            correctAnswerIndex = 1,
            Q = 3
        ),
        Question(
            content = "Choose the correct spelling:",
            options = listOf("Accomodate", "Acommodate", "Accommodate", "Accomadate"),
            correctAnswerIndex = 2,
            Q = 4
        ),
        Question(
            content = "What is the correct order of adjectives?",
            options = listOf(
                "Opinion - Size - Age - Shape - Color - Origin - Material - Purpose",
                "Size - Opinion - Age - Shape - Color - Origin - Material - Purpose",
                "Opinion - Age - Size - Shape - Color - Origin - Material - Purpose",
                "Size - Shape - Age - Color - Origin - Material - Opinion - Purpose"
            ),
            correctAnswerIndex = 0,
            Q = 5
        ),
        Question(
            content = "What does the idiom 'break the ice' mean?",
            options = listOf(
                "To destroy something",
                "To make people feel more comfortable in a social situation",
                "To cool down a drink",
                "To start a fight"
            ),
            correctAnswerIndex = 1,
            Q = 6
        ),
        Question(
            content = "Which of these is a correct conditional sentence?",
            options = listOf(
                "If I will see him, I will tell him.",
                "If I would see him, I will tell him.",
                "If I see him, I will tell him.",
                "If I see him, I would tell him."
            ),
            correctAnswerIndex = 2,
            Q = 7
        )
    )

    private val _weeklyQuestions = MutableStateFlow(
        WeeklyQuestions(
            week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR),
            questions = sampleQuestions
        )
    )
    val weeklyQuestions: StateFlow<WeeklyQuestions> = _weeklyQuestions.asStateFlow()

    private val _todayQuestion = MutableStateFlow<Question?>(null)
    val todayQuestion: StateFlow<Question?> = _todayQuestion.asStateFlow()

    init {
        loadTodayQuestion()
    }

    private fun loadTodayQuestion() {
//        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
//        // Calendar.DAY_OF_WEEK starts with Sunday=1, so we adjust to match our data model
//        val adjustedDay = if (currentDay == Calendar.SUNDAY) 7 else currentDay - 1
//
//        _todayQuestion.value = _weeklyQuestions.value.questions.find { it.Q == adjustedDay }
        _todayQuestion.value = getRandomQuestionForToday()
    }

    fun answerQuestion(questionId: String, selectedAnswer: Int) {
        val currentQuestions = _weeklyQuestions.value.questions.toMutableList()
        val questionIndex = currentQuestions.indexOfFirst { it.id == questionId }

        if (questionIndex != -1) {
            val updatedQuestion = currentQuestions[questionIndex].copy(
                isAnswered = true,
                userSelectedAnswer = selectedAnswer
            )

            currentQuestions[questionIndex] = updatedQuestion

            _weeklyQuestions.value = _weeklyQuestions.value.copy(questions = currentQuestions)

            // Update today's question if it's the same
            if (_todayQuestion.value?.id == questionId) {
                _todayQuestion.value = updatedQuestion
            }
        }
    }

    fun getRandomQuestionForToday(): Question {
//        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
//        // Calendar.DAY_OF_WEEK starts with Sunday=1, so we adjust to match our data model
//        val adjustedDay = if (currentDay == Calendar.SUNDAY) 7 else currentDay - 1
//
//        // Get questions for the current day
//        val todayQuestions = _weeklyQuestions.value.questions.filter { it.Q == adjustedDay }
//
//        return if (todayQuestions.isNotEmpty()) {
//            // Return a random question or the first one
//            todayQuestions.random()
//        } else {
//            // Fallback: return any random question
//            _weeklyQuestions.value.questions.random()
//        }
        return _weeklyQuestions.value.questions.random()
    }

    // Singleton pattern
    companion object {
        @Volatile
        private var INSTANCE: QuestionRepository? = null

        fun getInstance(): QuestionRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: QuestionRepository().also { INSTANCE = it }
            }
        }
    }
}