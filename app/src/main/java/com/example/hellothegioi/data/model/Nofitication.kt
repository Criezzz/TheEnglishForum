package com.example.hellothegioi.data.model


sealed class NotificationTarget(val route: String) {
    data class Post(val post: com.example.hellothegioi.data.model.Post) : NotificationTarget("post")
    data class ComposePage(val screen: String) : NotificationTarget(screen)
}

data class Notification(
    val messenger: String,
    val time: String,
    val target: NotificationTarget
)
