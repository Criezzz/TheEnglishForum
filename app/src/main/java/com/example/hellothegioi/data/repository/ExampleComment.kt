package com.example.hellothegioi.data.repository

import com.example.hellothegioi.data.model.Comment
import com.example.hellothegioi.R

object ExampleComment {
    private val exampleComment = listOf(
        Comment(
            userName = "Jack Davit",
            avatar = R.drawable.ic_user_avatar,
            time = "1 min",
            text = "Good idea"
        ),
        Comment(
            userName = "Lương Kết",
            avatar = R.drawable.ic_user_avatar,
            time = "3 day",
            text = "First Comment",
            likes = 1
        ),
        Comment(
            userName = "Trần Công",
            avatar = R.drawable.ic_user_avatar,
            time = "30 mins",
            text = "can i cooperate with you"
        ),
        Comment(
            userName = "Nam Nguyễn",
            avatar = R.drawable.ic_user_avatar,
            time = "1 hour",
            text = "Bro, you win",
            likes = 3,
            replies = 2
        ),
        Comment(
            userName = "Hoàng Hiệp",
            avatar = R.drawable.ic_user_avatar,
            time = "2 day",
            text = "He he, thank you"
        ),
        Comment(
            userName = "Donal Trump",
            avatar = R.drawable.ic_user_avatar,
            time = "45 min",
            text = "I can hire you"
        )
    )

    fun getAll(): List<Comment> = exampleComment
}