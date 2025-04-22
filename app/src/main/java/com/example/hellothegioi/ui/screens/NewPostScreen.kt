package com.example.hellothegioi.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.example.hellothegioi.R
import java.io.File
import java.io.FileOutputStream
import androidx. compose. ui. text. font. FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NewPostScreen(onBack: () -> Unit) {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val photoUri = remember { mutableStateOf<Uri?>(null) }

    val launcherGallery = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { imageUri.value = it }
    }

    val launcherCamera = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success && photoUri.value != null) {
            imageUri.value = photoUri.value
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = "Back", modifier = Modifier.size(25.dp))
            }

            Text(text = "New Post", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            IconButton(onClick = { /* handle draft */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_draft), contentDescription = "Save Draft", modifier = Modifier.size(25.dp))
            }
        }

        Spacer(modifier = Modifier.height(5.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(5.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_user_avatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = "NameUser", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(5.dp))

                BasicTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
                    decorationBox = { innerTextField ->
                        if (text.isEmpty()) {
                            Text("Write in....", color = Color.LightGray)
                        }
                        innerTextField()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.padding(start = 55.dp)) {
            IconButton(onClick = {
                val imageFile = File(context.cacheDir, "camera_photo.jpg")
                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.fileprovider",
                    imageFile
                )
                photoUri.value = uri
                launcherCamera.launch(uri)
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_camera), contentDescription = "Add Photo", modifier = Modifier.size(30.dp))
            }
            IconButton(onClick = { launcherGallery.launch("image/*") }) {
                Icon(painter = painterResource(id = R.drawable.ic_picture), contentDescription = "Add from Gallery", modifier = Modifier.size(25.dp))
            }
            IconButton(onClick = { /* handle add */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "Add", modifier = Modifier.size(25.dp))
            }
        }

        imageUri.value?.let { uri ->
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* handle post */ },
            modifier = Modifier.align(Alignment.End).padding(12.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ) {
            Text(text = "Post", color = Color.Black)
        }
    }
}

fun saveBitmapToCacheAndGetUri(context: Context, bitmap: Bitmap): Uri {
    val file = File(context.cacheDir, "captured_image_${System.currentTimeMillis()}.png")
    FileOutputStream(file).use {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
    }
    return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
}


@Preview(showBackground = true)
@Composable
fun NewPostScreenPreview(){
    NewPostScreen{}
}
