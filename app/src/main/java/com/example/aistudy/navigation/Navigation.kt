package com.example.aistudy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.aistudy.navigation.destinations.listComposable
import com.example.aistudy.navigation.destinations.noteComposable
import com.example.aistudy.navigation.destinations.splashComposable
import com.example.aistudy.navigation.destinations.chatbotComposable
import com.example.aistudy.ui.viewmodels.SharedViewModel
import com.example.aistudy.utils.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val screenRoutes = remember(navController) {
        ScreenRoutes(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        splashComposable(
            navigateToListScreen = screenRoutes.fromSplashToList
        )
        listComposable(
            navigateToNoteScreen = screenRoutes.fromListToNote,
            sharedViewModel = sharedViewModel
        )
        noteComposable(
            navigateToListScreen = screenRoutes.fromNoteToList,
            sharedViewModel = sharedViewModel,
            navigateToChatbotScreen = screenRoutes.fromNoteToChatbot
        )
        chatbotComposable(
            navigateToNoteScreen = screenRoutes.fromChatbottoNote,
            sharedViewModel = sharedViewModel
        )
    }
}