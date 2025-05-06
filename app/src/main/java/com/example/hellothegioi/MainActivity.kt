package com.example.hellothegioi

import android.os.Bundle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.hellothegioi.R
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hellothegioi.data.model.Post
import com.example.hellothegioi.data.model.User
import com.example.hellothegioi.data.repository.ExampleUser
import com.example.hellothegioi.ui.navigation.BottomNavigationBar
import com.example.hellothegioi.ui.screens.CFInstructor
import com.example.hellothegioi.ui.screens.CommentScreen
import com.example.hellothegioi.ui.screens.CreateScreen
import com.example.hellothegioi.ui.screens.ForgotPassword
import com.example.hellothegioi.ui.screens.HelpScreen
import com.example.hellothegioi.ui.screens.HomeScreen
import com.example.hellothegioi.ui.screens.LoginScreen
import com.example.hellothegioi.ui.screens.NewPostScreen
import com.example.hellothegioi.ui.screens.NotificationScreen
import com.example.hellothegioi.ui.screens.ProfileScreen
import com.example.hellothegioi.ui.screens.QuestionScreen
import com.example.hellothegioi.ui.screens.SearchScreen
import com.example.hellothegioi.ui.theme.HellothegioiTheme
import com.example.hellothegioi.ui.screens.QuestionDetailScreen
import com.example.hellothegioi.ui.screens.RePasswordScreen
import com.example.hellothegioi.ui.screens.UserProfileScreen
import com.example.hellothegioi.ui.screens.UserProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Khởi tạo SettingsViewModel
            val settingsViewModel: SettingsViewModel = viewModel()

            // Đọc các cài đặt từ SettingsViewModel
            val isDarkTheme = settingsViewModel.isDarkTheme.collectAsState().value
            val primaryColor = settingsViewModel.primaryColor.collectAsState().value
            val fontSize = settingsViewModel.fontSize.collectAsState().value
            val fontWeight = settingsViewModel.fontWeight.collectAsState().value
            HellothegioiTheme (
                darkTheme = isDarkTheme,
                primaryColor = primaryColor,
                fontSize = fontSize,
                fontWeight = fontWeight
            ){
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route

                // Shared ViewModel for user state
                val userProfileViewModel: UserProfileViewModel = viewModel()

                Scaffold(
                    bottomBar = {
                        if ((currentRoute != "login" && currentRoute != "create" &&
                                    currentRoute != "Cf" && currentRoute != "help"&&
                                currentRoute != "repassword") && currentRoute in listOf(
                                "home",
                                "search",
                                "question",
                                "notification",
                                "profile"
                            )
                        ) {
                            BottomNavigationBar(
                                selectedItem = currentRoute ?: "home",
                                onItemSelected = { route ->
                                    navController.navigate(route) {
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
                            startDestination = "login",
                        ) {
                            composable("login") {
                                 LoginScreen(
                                     onNavigateToCreate = {
                                         navController.navigate("create")
                                     },
                                     onNavigateToHome = {
                                         navController.navigate("home") {
                                             popUpTo("login") { inclusive = true }
                                         }
                                     },
                                        onNavigateToHelp = {
                                            navController.navigate("help")
                                        },
                                        onNavigateToFogotPass = {
                                            navController.navigate("fogotpassword")
                                        }
                                 )
                            }
                            composable("create") {
                                CreateScreen(
                                    onNavigateTologin = {
                                    navController.navigate("login")
                                    },
                                    onNavigateToInstructor = {
                                        navController.navigate("Cf")
                                    },
                                    onNavigateToHelp = {
                                        navController.navigate("help")
                                    }
                                )
                            }
                            composable("help") {
                                HelpScreen(
                                    onNavigateToLogin = {
                                        navController.navigate("login")
                                    }
                                )
                            }
                            composable("Cf") {
                                CFInstructor(
                                    onNavigateToLogin = {
                                        navController.navigate("login")
                                    }
                                )
                            }
                            composable("repassword") {
                                RePasswordScreen(
                                    onNavigateToHelp = {
                                        navController.navigate("help")
                                    },
                                    onNavigateToLogin = {
                                        navController.navigate("login")
                                    }
                                )
                            }
                            composable("home") {
                                HomeScreen(
                                    onNavigateToNewPost = {
                                        navController.navigate("newpost")
                                    },
                                    onNavigateToComment = { post ->
                                        navController.currentBackStackEntry?.savedStateHandle?.set(
                                            "post",
                                            post
                                        )
                                        navController.navigate("comment")
                                    }
                                )
                            }
                            composable("fogotpassword") {
                                ForgotPassword(
                                    onNavigateToLogin = {
                                        navController.navigate("login")
                                    },
                                    onNavigateToRepass = {
                                        navController.navigate("repassword")
                                    }
                                )
                            }
                            composable("search") { SearchScreen() }
                            composable("question") { QuestionScreen() }
                            composable("notification") { NotificationScreen(
                                onNavigateToComment = { post ->
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        "post",
                                        post
                                    )
                                    navController.navigate("comment")
                                },
                                onNavigateToQuestion = {
                                    navController.navigate("question")
                                }
                            ) }
                            composable("profile") {
                                ProfileScreen(
                                    user = userProfileViewModel.getUser(),
                                    onNavigateToComment = { post ->
                                        navController.currentBackStackEntry?.savedStateHandle?.set(
                                            "post",
                                            post
                                        )
                                        navController.navigate("comment")
                                    },
                                    onNavigateToProfileSetting = {
                                        navController.navigate("userProfile")
                                    },
                                    onNavigateToUISetting = {
                                        navController.navigate("uiSetting")
                                    }
                                )
                            }
                            composable("newpost") {
                                NewPostScreen(
                                    user = userProfileViewModel.getUser(),
                                    onBack = { navController.popBackStack() },
                                    onPost = { text, imageUri ->
                                        navController.popBackStack()
                                        println("text: $text and imageUri: $imageUri")
                                    }
                                )

                            }
                            composable("userProfile") {
                                UserProfileScreen(
                                    user = userProfileViewModel.getUser(),
                                    onBack = { navController.popBackStack("profile", inclusive = false) },
                                    onSave = { updatedUser ->
                                        userProfileViewModel.updateUser(updatedUser)
                                    }
                                )
                            }
                            composable("uiSetting") {
                                SettingsScreen(
                                    navController = navController,
                                    viewModel = settingsViewModel
                                )
                            }
                            composable("comment") {
                                val post =
                                    navController.previousBackStackEntry?.savedStateHandle?.get<Post>(
                                        "post"
                                    )
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