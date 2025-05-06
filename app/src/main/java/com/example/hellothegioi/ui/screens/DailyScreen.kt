package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star

import com.example.hellothegioi.data.model.Question

@Composable
fun DailyScreen(
    onTreasureClick: () -> Unit = {},
    onQuestionClick: (Question) -> Unit = {},
    viewedQuestions: List<Int> = emptyList(),
    questions: List<Question>
) {
    var searchText by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }

    val filterOptions = listOf("All", "Đã đọc", "Chưa đọc")

    val filteredPosts = questions.filter {
        when (selectedFilter) {
            "All" -> true
            "Đã đọc" -> it.id in viewedQuestions
            "Chưa đọc" -> it.id !in viewedQuestions
            else -> true
        }
    }.filter {
        it.content.contains(searchText, ignoreCase = true) || it.hashtag.contains(searchText, ignoreCase = true)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                var expanded by remember { mutableStateOf(false) }

                Box {
                    OutlinedButton(
                        onClick = { expanded = true },
                        modifier = Modifier
                            .width(120.dp)
                            .padding(end = 8.dp)
                    ) {
                        Text(selectedFilter)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown"
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        filterOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedFilter = option
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text("Tìm kiếm câu hỏi...") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = { /* Search logic */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Find", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            filteredPosts.forEach { question ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clickable { onQuestionClick(question) }
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "User Avatar",
                            modifier = Modifier
                                .size(36.dp)
                                .padding(end = 12.dp)
                        )
                        Column {
                            Text(
                                text = "Người dùng #${question.id}",
                                fontWeight = FontWeight.Medium,
                                style = MaterialTheme.typography.labelLarge
                            )
                            Text(
                                text = question.hashtag,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = question.content,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 4.dp, bottom = 6.dp)
                            )
                            Text(
                                text = question.date +
                                        if (question.id in viewedQuestions) " • Đã xem" else "",
                                style = MaterialTheme.typography.labelSmall,
                                color = if (question.id in viewedQuestions) Color(0xFF4CAF50) else Color.Gray
                            )
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = onTreasureClick,
            containerColor = Color(0xFFFFC107),
            contentColor = Color.Black,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Đổi thưởng"
            )
        }
    }
}