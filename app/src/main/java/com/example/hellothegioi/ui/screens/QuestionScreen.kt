package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun QuestionScreen(viewModel: QuestionViewModel = viewModel()) {
    var selectedQuestionId by remember { mutableStateOf<Int?>(null) }
    val viewedQuestions = remember { mutableStateListOf<Int>() }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Câu hỏi", fontSize = 24.sp)

        if (selectedQuestionId == null) {
            DailyScreen(
                viewedQuestions = viewedQuestions,
                onQuestionClick = { question ->
                    selectedQuestionId = question.id
                    if (question.id !in viewedQuestions) {
                        viewedQuestions.add(question.id)
                    }
                },
                questions = viewModel.questions
            )
        } else {
            val question = viewModel.getQuestionById(selectedQuestionId!!)
            if (question != null) {
                QuestionDetailScreen(
                    question = question,
                    onBack = { selectedQuestionId = null },
                    onSubmitAnswer = { answer ->
                        viewModel.addAnswerToQuestion(question.id, answer)
                    }
                )
            }
        }
    }
}