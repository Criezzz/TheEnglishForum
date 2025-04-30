package com.example.hellothegioi.data.repository

import com.example.hellothegioi.data.model.User

class ExampleUser {
    companion object {
        val student = User(
            name = "John Doe",
            role = "Student",
            follower = 1000,
            following = 100,
            bio = "This is a short bio about the student.",
            username = "john_doe",
            age = "20",
            gender = "Male",
            level = "B1",
            email = "john.doe@example.com",
            isVerifiedTeacher = false,
            password = "password123"
        )

        val teacher = User(
            name = "Jane Smith",
            role = "Teacher",
            follower = 2000,
            following = 300,
            bio = "Dedicated teacher with a passion for education.",
            username = "jane_smith",
            age = "35",
            gender = "Female",
            level = "C2",
            email = "jane.smith@example.com",
            isVerifiedTeacher = true,
            password = "securepassword"
        )
    }
}