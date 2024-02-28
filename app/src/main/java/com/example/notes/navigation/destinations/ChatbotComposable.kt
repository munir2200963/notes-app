package com.example.notes.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.notes.ui.screens.chatbot.ChatbotScreen
import com.example.notes.ui.screens.splash.SplashScreen
import com.example.notes.ui.viewmodels.SharedViewModel
import com.example.notes.utils.Constants.CHATBOT_SCREEN
import com.example.notes.utils.Constants.SPLASH_SCREEN

fun NavGraphBuilder.chatbotComposable(
    navigateToNoteScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(route = CHATBOT_SCREEN) {
        ChatbotScreen(
            navigateToNoteScreen = navigateToNoteScreen,
            sharedViewModel = sharedViewModel
        )
    }
}
