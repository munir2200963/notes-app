package com.example.aistudy.ui.screens.chatbot

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.aistudy.ui.viewmodels.SharedViewModel
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aistudy.R
import com.example.aistudy.components.ChatScreen
import com.example.aistudy.ui.theme.GeminiChatBotTheme
import com.example.aistudy.ui.viewmodels.ChatViewModel
import java.io.IOException

@Composable
fun ChatbotScreen(
    navigateToNoteScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val context = LocalContext.current
    val imageUriState = remember { mutableStateOf<Uri?>(null) }
    val chatViewModel = remember { ChatViewModel() } // Adjust according to how you obtain your ViewModel

    val imagePickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        imageUriState.value = uri
    }

    // Convert the URI to a Bitmap
    val bitmap = imageUriState.value?.let { uri ->
        context.toBitmap(uri)
    }

    GeminiChatBotTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .height(35.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.TopStart),
                            text = stringResource(id = R.string.app_name),
                            fontSize = 19.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            ) {
                ChatScreen(
                    paddingValues = it,
                    chatViewModel = chatViewModel,
                    imagePickerLauncher = { request ->
                        imagePickerLauncher.launch(request)
                    },
                    bitmap = bitmap
                )
            }
        }
    }
}

fun Context.toBitmap(uri: Uri): Bitmap? {
    return try {
        if (Build.VERSION.SDK_INT < 28) {
            @Suppress("DEPRECATION")
            MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        } else {
            val source = ImageDecoder.createSource(this.contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        }
    } catch (e: IOException) {
        null
    }
}


