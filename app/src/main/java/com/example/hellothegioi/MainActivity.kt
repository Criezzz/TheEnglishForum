package com.example.hellothegioi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hellothegioi.data.model.Post
import com.example.hellothegioi.ui.navigation.BottomNavigationBar
import com.example.hellothegioi.ui.theme.HellothegioiTheme
import com.example.hellothegioi.ui.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HellothegioiTheme {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        // setup bar
                        if (currentRoute in listOf("home", "search", "question", "notification", "profile")) {
                            BottomNavigationBar(
                                selectedItem = currentRoute ?: "home",
                                onItemSelected = { route ->
                                    navController.navigate(route) {
                                        // clear stack previous
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = "home"
                        ) {
                            composable("home") {
                                HomeScreen(
                                    onNavigateToNewPost = {
                                        navController.navigate("newpost")
                                    },
                                    onNavigateToComment = { post ->
                                        navController.currentBackStackEntry?.savedStateHandle?.set("post", post)
                                        navController.navigate("comment")
                                    }
                                )
                            }
                            composable("search") { SearchScreen() }
                            composable("question") { QuestionScreen() }
                            composable("notification") { NotificationScreen() }
                            composable("profile") { ProfileScreen() }

                            // new post screen
                            composable("newpost") {
                                NewPostScreen(
                                    onBack = { navController.popBackStack() }
                                )
                            }

                            composable("comment") {
                                val post = navController.previousBackStackEntry?.savedStateHandle?.get<Post>("post")
                                post?.let {
                                    CommentScreen(
                                        post = it,
                                        onBack = { navController.popBackStack() }
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

