package com.example.hellothegioi.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.hellothegioi.data.model.Question
import com.example.hellothegioi.data.model.WeeklyQuestions
import com.example.hellothegioi.ui.screens.QuestionViewModel_v2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class QuestionRepository(private val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("question_answers", Context.MODE_PRIVATE)

    private val sampleQuestions = listOf(
        Question(
            id = "1",
            content = "What's the correct meaning of 'endeavor'?",
            options = listOf("To try hard", "To give up", "To succeed", "To fail"),
            correctAnswerIndex = 0,
            Q = 1
        ),
        Question(
            id = "2",
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
            id = "3",
            content = "What's the antonym of 'benevolent'?",
            options = listOf("Kind", "Malevolent", "Generous", "Charitable"),
            correctAnswerIndex = 1,
            Q = 3
        ),
        Question(
            id = "4",
            content = "Choose the correct spelling:",
            options = listOf("Accomodate", "Acommodate", "Accommodate", "Accomadate"),
            correctAnswerIndex = 2,
            Q = 4
        ),
        Question(
            id = "5",
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
            id = "6",
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
            id = "7",
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
        loadSavedAnswers() // ✅ Khôi phục trạng thái từ SharedPreferences
    }

    private fun loadTodayQuestion() {
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

            saveUserAnswer(questionId, selectedAnswer) // ✅ Lưu câu trả lời vào SharedPreferences
        }
    }

    private fun saveUserAnswer(questionId: String, selectedAnswer: Int) {
        sharedPreferences.edit()
            .putInt("${questionId}_answer", selectedAnswer)
            .putBoolean("${questionId}_answered", true) // ✅ Lưu trạng thái đã trả lời
            .apply()
    }

    private fun getUserAnswer(questionId: String): Pair<Int?, Boolean> {
        val savedAnswer = sharedPreferences.getInt("${questionId}_answer", -1)
        val isAnswered = sharedPreferences.getBoolean("${questionId}_answered", false)

        return if (savedAnswer != -1) Pair(savedAnswer, isAnswered) else Pair(null, false)
    }

    fun loadSavedAnswers(): Map<String, QuestionViewModel_v2.AnswerResult> {
        val savedResults = mutableMapOf<String, QuestionViewModel_v2.AnswerResult>()

        _weeklyQuestions.value.questions.forEach { question ->
            val (savedAnswer, isAnswered) = getUserAnswer(question.id)
            if (savedAnswer != null) {
                val result = if (savedAnswer == question.correctAnswerIndex) {
                    QuestionViewModel_v2.AnswerResult.Correct
                } else {
                    QuestionViewModel_v2.AnswerResult.Incorrect(question.correctAnswerIndex)
                }
                savedResults[question.id] = result
            }
        }

        return savedResults // ✅ Trả về Map cho ViewModel cập nhật
    }

    fun getRandomQuestionForToday(): Question {
        return _weeklyQuestions.value.questions.random()
    }

    companion object {
        @Volatile
        private var INSTANCE: QuestionRepository? = null

        fun getInstance(context: Context): QuestionRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: QuestionRepository(context).also { INSTANCE = it }
            }
        }
    }
}
