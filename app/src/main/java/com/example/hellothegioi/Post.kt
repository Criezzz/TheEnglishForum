package com.example.hellothegioi

data class Post(
    var avatar: Int,
    var ownerName: String,
    var postDate: String,
    var isDraft: Boolean,
    var title: String,
    var text: String,
    var image: Int? = null,
    var likes: Int = 0,
    var comments: Int = 0,
    var onClick: () -> Unit
)
