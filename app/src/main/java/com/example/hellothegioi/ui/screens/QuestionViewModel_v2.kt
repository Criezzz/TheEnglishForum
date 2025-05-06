package com.example.hellothegioi.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hellothegioi.data.model.Question
import com.example.hellothegioi.data.model.WeeklyQuestions
import com.example.hellothegioi.data.repository.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionViewModel_v2(private val repository: QuestionRepository) : ViewModel() {

    val weeklyQuestions: StateFlow<WeeklyQuestions> = repository.weeklyQuestions
    val todayQuestion: StateFlow<Question?> = repository.todayQuestion

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // Lưu trạng thái câu trả lời theo từng questionId
    private val _answerResults = MutableStateFlow<Map<String, AnswerResult>>(emptyMap())
    val answerResults: StateFlow<Map<String, AnswerResult>> = _answerResults.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = UiState.Success
        }
    }

    fun answerQuestion(questionId: String, selectedAnswer: Int) {
        val question = weeklyQuestions.value.questions.find { it.id == questionId }

        if (question != null) {
            repository.answerQuestion(questionId, selectedAnswer)

            val result = if (selectedAnswer == question.correctAnswerIndex) {
                AnswerResult.Correct
            } else {
                AnswerResult.Incorrect(question.correctAnswerIndex)
            }

            _answerResults.update { currentMap ->
                currentMap + (questionId to result)
            }
        }
    }

    fun resetAnswerResult(questionId: String) {
        _answerResults.update { currentMap ->
            currentMap - questionId
        }
    }

    sealed class UiState {
        object Loading : UiState()
        object Success : UiState()
        data class Error(val message: String) : UiState()
    }

    sealed class AnswerResult {
        object Correct : AnswerResult()
        data class Incorrect(val correctAnswer: Int) : AnswerResult()
    }

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuestionViewModel_v2::class.java)) {
                return QuestionViewModel_v2(QuestionRepository.getInstance()) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
