package com.example.qdaily
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileReader
import java.io.InputStreamReader

class QuestionViewModel(application: Application) : AndroidViewModel(application) {
    private val _questions = mutableStateListOf<Question>()
    val questions: List<Question> get() = _questions

    init {
        loadQuestionsFromJson()
    }

    private fun loadQuestionsFromJson() {
        val context = getApplication<Application>().applicationContext

        // Mở file từ thư mục filesDir của ứng dụng
        val file = File(context.filesDir, "questions_saved.json")

        // Kiểm tra nếu file tồn tại
        if (file.exists()) {
            val reader = FileReader(file)
            val questionType = object : TypeToken<List<Question>>() {}.type
            val list: List<Question> = Gson().fromJson(reader, questionType)
            _questions.addAll(list)
            reader.close()
        } else {
            Log.wtf("loadQuestionsFromJson", "File not found!")
        }
    }

    fun getQuestionById(id: Int): Question? = _questions.find { it.id == id }

    fun addAnswerToQuestion(questionId: Int, answer: String) {
        val index = _questions.indexOfFirst { it.id == questionId }
        if (index != -1) {
            val question = _questions[index]
            _questions[index] = question.copy(answers = question.answers + answer)
            saveQuestionsToJson() // Lưu lại
        }
    }

    private fun saveQuestionsToJson() {
        val json = Gson().toJson(_questions)
        val context = getApplication<Application>().applicationContext
        val file = File(context.filesDir, "questions_saved.json")
        Log.wtf("done", file.path)
        file.writeText(json)
    }
}
class UserProfileViewModel(application: Application) : AndroidViewModel(application) {
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

    fun saveProfile() {
        viewModelScope.launch {
            val context = getApplication<Application>().applicationContext
            val file = File(context.filesDir, fileName)

            val profile = UserProfileData(
                username.value,
                age.value,
                role.value,
                gender.value,
                level.value,
                email.value,
                isVerifiedTeacher.value
            )

            val json = Gson().toJson(profile)
            file.writeText(json)
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            val context = getApplication<Application>().applicationContext
            val file = File(context.filesDir, fileName)

            val json: String = if (file.exists()) {
                file.readText()
            } else {
                // Đọc từ assets nếu chưa có file user_profile.json trong bộ nhớ trong
                context.assets.open("user.json").bufferedReader().use { it.readText() }
            }

            try {
                val profile = Gson().fromJson(json, UserProfileData::class.java)
                username.value = profile.username
                age.value = profile.age
                role.value = profile.role
                gender.value = profile.gender
                level.value = profile.level
                email.value = profile.email
                isVerifiedTeacher.value = profile.isVerifiedTeacher
            } catch (e: Exception) {
                // Nếu JSON lỗi thì gán mặc định
                username.value = "Username"
                age.value = "20"
                role.value = "Học sinh"
                gender.value = "Nam"
                level.value = "B1"
                email.value = "usernam@gmai.com"
                isVerifiedTeacher.value = false
            }
        }
    }


    fun resetTeacherVerification() {
        if (role.value == "Giáo viên") {
            isVerifiedTeacher.value = false
        }
    }
}
