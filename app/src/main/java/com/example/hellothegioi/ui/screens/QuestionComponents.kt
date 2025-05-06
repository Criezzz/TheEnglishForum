package com.example.hellothegioi.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.ui.screens.Question
import com.example.hellothegioi.ui.screens.WeeklyQuestions
import com.example.hellothegioi.ui.screens.QuestionRepository
import com.example.hellothegioi.ui.screens.QuestionViewModel_v2

@Composable
fun TodayQuestionCard(
    question: Question,
    onAnswerSelected: (Int) -> Unit,
    answerResult: QuestionViewModel_v2.AnswerResult?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Today's Question",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = question.content,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            question.options.forEachIndexed { index, option ->
                val isSelected = question.userSelectedAnswer == index
                val isCorrect = answerResult != null && index == question.correctAnswerIndex
                val isIncorrect = answerResult is QuestionViewModel_v2.AnswerResult.Incorrect &&
                        index == question.userSelectedAnswer

                val backgroundColor = when {
                    isCorrect -> Color(0xFFD5F5E3) // Light green for correct
                    isIncorrect -> Color(0xFFFADBD8) // Light red for incorrect
                    isSelected -> MaterialTheme.colorScheme.primaryContainer
                    else -> MaterialTheme.colorScheme.surface
                }

                val borderColor = when {
                    isCorrect -> Color(0xFF2ECC71) // Green for correct
                    isIncorrect -> Color(0xFFE74C3C) // Red for incorrect
                    isSelected -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.outline
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            width = 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(backgroundColor)
                        .clickable(
                            enabled = answerResult == null && !question.isAnswered,
                            onClick = { onAnswerSelected(index) }
                        )
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${('A' + index)}. $option",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyMedium
                        )

                        if (isCorrect) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Correct",
                                tint = Color(0xFF2ECC71)
                            )
                        } else if (isIncorrect) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Incorrect",
                                tint = Color(0xFFE74C3C)
                            )
                        }
                    }
                }
            }

            AnimatedVisibility(
                visible = answerResult != null,
                enter = fadeIn() + expandVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = when (answerResult) {
                            is QuestionViewModel_v2.AnswerResult.Correct -> "Correct!"
                            is QuestionViewModel_v2.AnswerResult.Incorrect -> "Incorrect!"
                            else -> ""
                        },
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = when (answerResult) {
                            is QuestionViewModel_v2.AnswerResult.Correct -> Color(0xFF2ECC71)
                            is QuestionViewModel_v2.AnswerResult.Incorrect -> Color(0xFFE74C3C)
                            else -> MaterialTheme.colorScheme.onSurface
                        }
                    )

                    if (answerResult is QuestionViewModel_v2.AnswerResult.Incorrect) {
                        Text(
                            text = "The correct answer is ${('A' + question.correctAnswerIndex)}",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun WeeklyQuestionItem(
    question: Question,
    onClick: () -> Unit,
    answerResult: QuestionViewModel_v2.AnswerResult?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicator for answered questions
            // Indicator for answered questions
            if (question.isAnswered) {
                if (answerResult is QuestionViewModel_v2.AnswerResult.Correct) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Answered",
                        tint = Color(0xFF2ECC71),  // Màu xanh cho đáp án đúng
                        modifier = Modifier.size(24.dp)
                    )
                } else if (answerResult is QuestionViewModel_v2.AnswerResult.Incorrect) {
                    Icon(
                        imageVector = Icons.Default.Close,  // Sử dụng X đỏ cho đáp án sai
                        contentDescription = "Answered",
                        tint = Color(0xFFE74C3C),  // Màu đỏ cho đáp án sai
                        modifier = Modifier.size(24.dp)
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(12.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Q ${question.Q}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = question.content,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}