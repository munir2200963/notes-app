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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.aistudy.R
import com.example.aistudy.components.ChatScreen
import com.example.aistudy.components.CustomText
import com.example.aistudy.ui.theme.BlackOlive
import com.example.aistudy.ui.theme.BlackShade
import com.example.aistudy.ui.theme.GeminiChatBotTheme
import com.example.aistudy.ui.theme.Pink40
import com.example.aistudy.ui.viewmodels.ChatViewModel
import com.example.aistudy.utils.Action
import java.io.IOException

@Composable
fun ChatbotScreen(
    navigateToNoteScreen: (Int) -> Unit,
    navController: NavHostController, // Add NavController parameter
    sharedViewModel: SharedViewModel
) {
    val context = LocalContext.current
    val imageUriState = remember { mutableStateOf<Uri?>(null) }
    val chatViewModel = remember { ChatViewModel() }

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
            color = BlackShade
        ) {
            Scaffold(
                topBar = { ChatbotBar(onBackAction = { navController.popBackStack() }) }, // Pass the no-parameter lambda directly
                content = { paddingValues ->
                    ChatScreen(
                        paddingValues = paddingValues,
                        chatViewModel = chatViewModel,
                        imagePickerLauncher = { request ->
                            imagePickerLauncher.launch(request)
                        },
                        bitmap = bitmap
                    )
                }
            )
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

@Composable
fun ChatbotBar(onBackAction: () -> Unit) {
    TopAppBar(
        elevation = 0.dp,
        navigationIcon = {
            Divider(modifier = Modifier.width(12.dp), color = BlackShade)
            BackButton(onBackAction) // The lambda here is directly usable
        },
        title = {
            CustomText(
                text = stringResource(id = R.string.chatbot_name),
                color = androidx.compose.material.MaterialTheme.colors.secondary,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            )
        },
        backgroundColor = BlackShade,
        actions = {
            Divider(modifier = Modifier.width(12.dp), color = BlackShade)
        }
    )
}

@Composable
fun BackButton(backButtonPressed: () -> Unit) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(color = BlackOlive, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = backButtonPressed), // Invoke the passed lambda on click
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}
