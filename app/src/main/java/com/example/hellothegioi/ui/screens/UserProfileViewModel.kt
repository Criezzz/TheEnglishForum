package com.example.hellothegioi.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.hellothegioi.data.model.CurrentUser
import java.io.File
import com.example.hellothegioi.data.model.User
import com.example.hellothegioi.data.repository.ExampleUser
import com.google.gson.Gson

class UserProfileViewModel(application: Application) : AndroidViewModel(application) {
    var name = mutableStateOf("")
    var username = mutableStateOf("")
    var age = mutableStateOf("")
    var role = mutableStateOf("")
    var gender = mutableStateOf("")
    var level = mutableStateOf("")
    var email = mutableStateOf("")
    var isVerifiedTeacher = mutableStateOf(false)

    val roleOptions = listOf("Học sinh", "Giáo viên")
    val genderOptions = listOf("Nam", "Nữ", "LGBT")
    val levelOptions = listOf("A1", "A2", "B1", "B2", "C1", "C2")

    private val fileName = "user.json"

    init {
        loadProfile()
    }
    fun updateUser(updatedUser: User) {
        name.value = updatedUser.name
        username.value = updatedUser.username
        age.value = updatedUser.age
        role.value = updatedUser.role
        gender.value = updatedUser.gender
        level.value = updatedUser.level
        email.value = updatedUser.email
        isVerifiedTeacher.value = updatedUser.isVerifiedTeacher
        saveProfile()
    }
    fun getUser(): User {
        return User(
            name = name.value,
            username = username.value,
            age = age.value,
            role = role.value,
            gender = gender.value,
            level = level.value,
            email = email.value,
            isVerifiedTeacher = isVerifiedTeacher.value,
            follower = ExampleUser.student.follower,
            following = ExampleUser.student.following,
            bio = ExampleUser.student.bio,
            password = ExampleUser.student.password
        )
    }

    fun saveProfile() {
        viewModelScope.launch {
            val context = getApplication<Application>().applicationContext
            val file = File(context.filesDir, fileName)

            // Load existing user to keep immutable fields
            val existingUser: User? = if (file.exists()) {
                Gson().fromJson(file.readText(), User::class.java)
            } else ExampleUser.student

            // Create updated user object
            val updatedUser = User(
                name = name.value,
                username = existingUser?.username ?: ExampleUser.student.username,
                role = role.value,
                follower = existingUser?.follower ?: ExampleUser.student.follower,
                following = existingUser?.following ?: ExampleUser.student.following,
                bio = existingUser?.bio ?: ExampleUser.student.bio,
                age = age.value,
                gender = gender.value,
                level = level.value,
                email = email.value,
                isVerifiedTeacher = isVerifiedTeacher.value,
                password = existingUser?.password ?: ExampleUser.student.password
            )

            // Update CurrentUser
            CurrentUser.user = updatedUser

            // Save updated user to JSON
            val json = Gson().toJson(updatedUser)
            file.writeText(json)
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            // Always load from ExampleUser.student
            val defaultUser = ExampleUser.student
            name.value = defaultUser.name
            username.value = defaultUser.username
            age.value = defaultUser.age
            role.value = defaultUser.role
            gender.value = defaultUser.gender
            level.value = defaultUser.level
            email.value = defaultUser.email
            isVerifiedTeacher.value = defaultUser.isVerifiedTeacher
        }
    }

    fun resetTeacherVerification() {
        if (role.value == "Giáo viên") {
            isVerifiedTeacher.value = false
        }
    }
}