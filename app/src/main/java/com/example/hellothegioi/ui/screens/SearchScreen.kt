package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellothegioi.R
import com.example.hellothegioi.data.repository.ExamplePost
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.LazyColumn
import com.example.hellothegioi.ui.componets.PostItemHorizontal
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var sortOption by remember { mutableStateOf("none") }

    val allPosts = ExamplePost.getAll()

    // filler
    var filteredPosts = if (searchText.isBlank()) {
        allPosts
    } else {
        allPosts.filter {
            it.title.contains(searchText, ignoreCase = true) ||
                    it.text.contains(searchText, ignoreCase = true)
        }
    }

    //sort option
    filteredPosts = when (sortOption) {
        "likes" -> filteredPosts.sortedByDescending { it.likes }
        "time" -> filteredPosts.sortedByDescending { it.postTimeMillis }
        else -> filteredPosts
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Search",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Divider(color = Color.Gray, thickness = 1.dp)

        Spacer(modifier = Modifier.height(12.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search", color = Color.LightGray) },
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = "Filter Icon",
                            tint = Color.Gray,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFDDDDDD),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color(0xFFDDDDDD), shape = RoundedCornerShape(16.dp))
            ) {
                DropdownMenuItem(
                    text = { Text("Sắp xếp theo lượt thích") },
                    onClick = {
                        sortOption = "likes"
                        expanded = false
                    }
                )
                Divider()
                DropdownMenuItem(
                    text = { Text("Sắp xếp theo thời gian") },
                    onClick = {
                        sortOption = "time"
                        expanded = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(filteredPosts) { post ->
                PostItemHorizontal(post = post, onNavigateToComment = {})
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview(){
    SearchScreen()
}
