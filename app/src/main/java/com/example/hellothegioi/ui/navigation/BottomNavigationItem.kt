package com.example.hellothegioi.ui.navigation

import com.example.hellothegioi.R
import androidx.annotation.DrawableRes

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val icon: Int
) {
    object Home : BottomNavItem("home", R.drawable.ic_home)
    object Search : BottomNavItem("search", R.drawable.ic_search)
    object Menu : BottomNavItem("question", R.drawable.ic_question)
    object Notification : BottomNavItem("notification", R.drawable.ic_notify)
    object Profile : BottomNavItem("profile", R.drawable.ic_profile)
}

