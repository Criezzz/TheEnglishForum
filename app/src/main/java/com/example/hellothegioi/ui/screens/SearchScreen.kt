package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.R
import com.example.hellothegioi.data.repository.ExamplePost
import com.example.hellothegioi.ui.componets.PostItemHorizontal
import androidx.compose.foundation.layout.WindowInsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var sortOption by remember { mutableStateOf("none") }

    val allPosts = ExamplePost.getAll()

    var filteredPosts = if (searchText.isBlank()) {
        allPosts
    } else {
        allPosts.filter {
            it.title.contains(searchText, ignoreCase = true) ||
                    it.text.contains(searchText, ignoreCase = true)
        }
    }

    filteredPosts = when (sortOption) {
        "likes" -> filteredPosts.sortedByDescending { it.likes }
        "time" -> filteredPosts.sortedByDescending { it.postTimeMillis }
        else -> filteredPosts
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Search",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .fillMaxWidth()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text("Search", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                    trailingIcon = {
                        Box {
                            IconButton(onClick = { expanded = true }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_filter),
                                    contentDescription = "Filter Icon",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(25.dp)
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Sort by Likes") },
                                    onClick = {
                                        sortOption = "likes"
                                        expanded = false
                                    }
                                )
                                Divider()
                                DropdownMenuItem(
                                    text = { Text("Sort by Time") },
                                    onClick = {
                                        sortOption = "time"
                                        expanded = false
                                    }
                                )
                            }
                        }
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            RoundedCornerShape(12.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(vertical = 8.dp)
                    .border(
                        BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                        RoundedCornerShape(12.dp)
                    )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    items(filteredPosts) { post ->
                        PostItemHorizontal(post = post, onNavigateToComment = {})
                        Divider(
                            color = MaterialTheme.colorScheme.outline,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

