package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.ui.screens.Question
import com.example.hellothegioi.ui.screens.TodayQuestionCard
import com.example.hellothegioi.ui.screens.WeeklyQuestionItem
import com.example.hellothegioi.ui.screens.QuestionViewModel_v2
import kotlinx.coroutines.launch
import perfetto.protos.UiState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyQuestionScreen(
    viewModel: QuestionViewModel_v2,
    modifier: Modifier = Modifier
) {
    val weeklyQuestions by viewModel.weeklyQuestions.collectAsState()
    val todayQuestion by viewModel.todayQuestion.collectAsState()
    val answerResults by viewModel.answerResults.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var showFlashcard by remember { mutableStateOf(false) }
    var selectedQuestion by remember { mutableStateOf<Question?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "The Forum English",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is QuestionViewModel_v2.UiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is QuestionViewModel_v2.UiState.Error -> {
                    Text(
                        text = (uiState as QuestionViewModel_v2.UiState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }

                is QuestionViewModel_v2.UiState.Success -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Today's Question Section
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Daily Question Challenge",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Test your English knowledge with daily questions!",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = {
                                        selectedQuestion = todayQuestion
                                        if (selectedQuestion != null) {
                                            showFlashcard = true
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("View Today's Question")
                                }
                            }
                        }

                        // Weekly Questions Section
                        Text(
                            text = "This Week's Questions",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 8.dp)
                        ) {
                            items(weeklyQuestions.questions) { question ->
                                WeeklyQuestionItem(
                                    question = question,
                                    answerResult = answerResults[question.id],
                                    onClick = { }
                                )
                            }
                        }
                    }

                    // Floating Action Button thủ công ở giữa đáy màn hình
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        FloatingActionButton(
                            onClick = {
                                // TODO: FAB Action
                            },
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rewards",
                                tint = Color.Yellow
                            )
                        }
                    }

                    // Modal Bottom Sheet for Question Flashcard
                    if (showFlashcard && selectedQuestion != null) {
                        ModalBottomSheet(
                            onDismissRequest = {
                                showFlashcard = false
                            },
                            sheetState = sheetState
                        ) {
                            TodayQuestionCard(
                                question = selectedQuestion!!,
                                onAnswerSelected = { selectedAnswer ->
                                    viewModel.answerQuestion(selectedQuestion!!.id, selectedAnswer)
                                },
                                answerResult = answerResults[selectedQuestion!!.id],
                                modifier = Modifier.padding(bottom = 32.dp)
                            )
                        }
                    }
                }

                is QuestionViewModel_v2.UiState.Error -> TODO()
                QuestionViewModel_v2.UiState.Loading -> TODO()
                QuestionViewModel_v2.UiState.Success -> TODO()
            }
        }
    }
}
