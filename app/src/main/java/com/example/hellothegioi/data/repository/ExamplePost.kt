package com.example.hellothegioi.data.repository

import com.example.hellothegioi.R
import com.example.hellothegioi.data.model.Post
import com.example.hellothegioi.data.model.User
import com.example.hellothegioi.data.model.CurrentUser

object ExamplePost{
    private val examplePosts = listOf(
        Post(
            avatar = R.drawable.ic_user_avatar,
            ownerName = "John doe",
            postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 3,
            title = "Welcome to the Forum!",
            text = "This is our first post. Let's build a great community together!",
            image = R.drawable.image_test,
            isDraft = false,
            likes = 1
        ),
        Post(
            avatar = R.drawable.ic_user_avatar,
            ownerName = "Davit",
            postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 4,
            title = "Today's question!",
            text = "What can you never see but is constantly right in front of you?",
            isDraft = false,
            likes = 2
        ),
        Post(
            avatar = R.drawable.ic_user_avatar,
            ownerName = "Trần Công",
            postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 4,
            text = "Mọi người cho mình hỏi 1 số trung tâm tiếng anh uy tín với ạ!",
            image = R.drawable.image_test,
            isDraft = false,
            likes = 3
        ),
        Post(
            avatar = R.drawable.ic_user_avatar,
            ownerName = "Cô Mai Phương",
            postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 4,
            title = "Answer the question",
            text = "She _____ to Nha Trang every summer.\nA.goes\nB.went\nC.will go\nD.is going",
            isDraft = false,
            likes = 4
        ),
        Post(
            avatar = R.drawable.ic_user_avatar,
            ownerName = "Michael",
            postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 4,
            text = "I am lazy to learn English",
            image = R.drawable.image_test,
            isDraft = false,
            likes = 5
        )
    )

    fun getUserPost(): List<Post> {
        val currentUser = CurrentUser.user ?: return emptyList()
        return listOf(
            Post(
                avatar = R.drawable.ic_user_avatar,
                ownerName = currentUser.name,
                postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 2,
                title = "Tự học Tiếng Anh",
                text = "Mình đang luyện IELTS mỗi ngày. Các bạn có nguồn tài liệu nào hay không?",
                isDraft = false,
                likes = 5
            ),
            Post(
                avatar = R.drawable.ic_user_avatar,
                ownerName = currentUser.name,
                postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 1,
                text = "Mọi người thích học qua phim hay nhạc hơn?",
                isDraft = false,
                likes = 5
            )
        )
    }

    fun getAll(): List<Post> = examplePosts + getUserPost()
}
