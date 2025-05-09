package com.example.hellothegioi.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hellothegioi.data.model.User
import com.example.hellothegioi.data.model.CurrentUser
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    user: User,
    onBack: () -> Unit,
    onSave: (User) -> Unit
) {
    val viewModel: UserProfileViewModel = viewModel()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "User Profile",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = viewModel.name.value,
                        onValueChange = { viewModel.name.value = it },
                        label = { Text("Tên người dùng") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.age.value,
                        onValueChange = { viewModel.age.value = it },
                        label = { Text("Tuổi") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )

                    ExposedDropdownField("Vai trò", viewModel.role.value, viewModel.roleOptions) {
                        viewModel.role.value = it
                        viewModel.resetTeacherVerification()
                    }

                    if (viewModel.role.value == "Giáo viên" && !viewModel.isVerifiedTeacher.value) {
                        Text(
                            text = "Vui lòng chờ xác minh giấy tờ từ quản trị viên.",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    ExposedDropdownField("Giới tính", viewModel.gender.value, viewModel.genderOptions) {
                        viewModel.gender.value = it
                    }
                    ExposedDropdownField("Trình độ", viewModel.level.value, viewModel.levelOptions) {
                        viewModel.level.value = it
                    }

                    OutlinedTextField(
                        value = viewModel.email.value,
                        onValueChange = {},
                        label = { Text("Email") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        OutlinedButton(onClick = { /* Đổi mật khẩu */ }) {
                            Text("Đổi mật khẩu")
                        }
                        Button(
                            onClick = {
                                val updatedUser = User(
                                    name = viewModel.name.value,
                                    username = user.username,
                                    age = viewModel.age.value,
                                    role = viewModel.role.value,
                                    gender = viewModel.gender.value,
                                    level = viewModel.level.value,
                                    email = viewModel.email.value,
                                    isVerifiedTeacher = viewModel.isVerifiedTeacher.value,
                                    follower = user.follower,
                                    following = user.following,
                                    bio = user.bio,
                                    password = user.password
                                )
                                onSave(updatedUser)
                                CurrentUser.user = updatedUser
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Profile saved successfully!")
                                }
                            }
                        ) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownField(
    label: String,
    selected: String,
    options: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    Modifier.clickable { expanded = !expanded }
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .padding(top = 4.dp)
        ) {
            options.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        onSelected(it)
                        expanded = false
                    }
                )
            }
        }
    }
}


