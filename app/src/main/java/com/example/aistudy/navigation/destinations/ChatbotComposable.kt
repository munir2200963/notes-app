package com.example.aistudy.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.aistudy.ui.screens.chatbot.ChatbotScreen
import com.example.aistudy.ui.viewmodels.SharedViewModel
import com.example.aistudy.utils.Constants.CHATBOT_SCREEN

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
