package com.example.aistudy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.aistudy.navigation.destinations.ARComposable
import com.example.aistudy.navigation.destinations.Image2TextComposable
import com.example.aistudy.navigation.destinations.Speech2TextComposable
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
            navigateToNoteScreen = screenRoutes.fromFunctiontoNote,
            sharedViewModel = sharedViewModel,
            navigateToARScreen = screenRoutes.fromListToAR
        )
        noteComposable(
            navigateToListScreen = screenRoutes.fromNoteToList,
            navigateToChatbotScreen = screenRoutes.fromNoteToChatbot,
            navigateToImage2TextScreen = screenRoutes.fromNoteToImage2Text,
            navigateToSpeech2TextScreen = screenRoutes.fromNoteToSpeech2Text,
            sharedViewModel = sharedViewModel
        )
        chatbotComposable(
            navigateToNoteScreen = screenRoutes.fromFunctiontoNote,
            sharedViewModel = sharedViewModel,
            navController = navController
        )
        Image2TextComposable(
            navigateToNoteScreen = screenRoutes.fromFunctiontoNote,
            sharedViewModel = sharedViewModel
        )
        Speech2TextComposable(
            navigateToNoteScreen = screenRoutes.fromFunctiontoNote,
            sharedViewModel = sharedViewModel
        )
        ARComposable(
            navigateToListScreen = screenRoutes.fromARtoList,
            sharedViewModel = sharedViewModel
        )

    }
}