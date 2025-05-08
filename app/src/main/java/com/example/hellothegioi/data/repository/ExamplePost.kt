package com.example.hellothegioi.data.repository

import com.example.hellothegioi.R
import com.example.hellothegioi.data.model.Post
import com.example.hellothegioi.data.model.User
import com.example.hellothegioi.data.model.CurrentUser

object ExamplePost {
    private val teacherPosts = listOf(
        Post(
            avatar = R.drawable.ic_user_avatar,
            ownerName = "Jane Smith",
            postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 3,
            title = "Teaching Tips",
            text = "Here are some effective methods for teaching English to beginners. Let's discuss!",
            image = R.drawable.image_test,
            isDraft = false,
            likes = 15
        ),
        Post(
            avatar = R.drawable.ic_user_avatar,
            ownerName = "Jane Smith",
            postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 4,
            title = "Grammar Lesson",
            text = "Today's grammar topic: Present Perfect vs Past Simple. Who needs help with this?",
            isDraft = false,
            likes = 12
        )
    )

    private val studentPosts = listOf(
        Post(
            avatar = R.drawable.ic_user_avatar,
            ownerName = "John Doe",
            postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 2,
            title = "Tự học Tiếng Anh",
            text = "Mình đang luyện IELTS mỗi ngày. Các bạn có nguồn tài liệu nào hay không?",
            isDraft = false,
            likes = 5
        ),
        Post(
            avatar = R.drawable.ic_user_avatar,
            ownerName = "John Doe",
            postTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 1,
            text = "Mọi người thích học qua phim hay nhạc hơn?",
            isDraft = false,
            likes = 5
        )
    )

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
        
        // Get the appropriate posts based on user role
        val basePosts = if (currentUser.role == "Teacher") {
            teacherPosts
        } else {
            studentPosts
        }

        // Update the owner name in all posts to match the current user
        return basePosts.map { post ->
            post.copy(ownerName = currentUser.name)
        }
    }

    fun getAll(): List<Post> = examplePosts + getUserPost()
}
