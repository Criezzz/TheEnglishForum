package com.example.hellothegioi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hellothegioi.ui.screens.*
import com.example.hellothegioi.ui.theme.HellothegioiTheme
import com.example.hellothegioi.ui.navigation.BottomNavigationBar
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HellothegioiTheme {
                var selectedItem by remember { mutableStateOf("home") }

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            selectedItem = selectedItem,
                            onItemSelected = { selectedItem = it }
                        )
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)
                    ) {
                        when (selectedItem) {
                            "home" -> HomeScreen()
                            "search" -> SearchScreen()
                            "question" -> QuestionScreen()
                            "notification" -> NotificationScreen()
                            "profile" -> ProfileScreen()
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun MainScreen() {
//    Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->
//        ProfileScreen(
//            name = "John Doe",
//            role = "Student",
//            follower = 1000,
//            following = 100,
//            bio = "This is a short bio about the user. It can be expanded to show more details.",
//            onEditProfile = { /* Handle edit profile */ },
//            modifier = Modifier.padding(contentPadding)
//        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    HellothegioiTheme {
//        MainScreen()
//    }
//}