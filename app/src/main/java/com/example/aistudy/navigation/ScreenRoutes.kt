package com.example.aistudy.navigation

import androidx.navigation.NavController
import com.example.aistudy.utils.Action
import com.example.aistudy.utils.Constants.AR_SCREEN
import com.example.aistudy.utils.Constants.CHATBOT_SCREEN
import com.example.aistudy.utils.Constants.IMAGE2TEXT_SCREEN
import com.example.aistudy.utils.Constants.LIST_SCREEN
import com.example.aistudy.utils.Constants.SPEECH2TEXT_SCREEN
import com.example.aistudy.utils.Constants.SPLASH_SCREEN

class ScreenRoutes(navController: NavController) {

    val fromNoteToList: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

    val fromSplashToList: () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION.name}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    val fromListToAR: () -> Unit = {
        navController.navigate(route = AR_SCREEN)
    }

    val fromARtoList:  (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

    val fromNoteToChatbot: () -> Unit = {
        navController.navigate(route = CHATBOT_SCREEN)
    }

    val fromNoteToImage2Text: () -> Unit = {
        navController.navigate(route = IMAGE2TEXT_SCREEN)
    }

    val fromNoteToSpeech2Text: () -> Unit = {
        navController.navigate(route = SPEECH2TEXT_SCREEN)
    }

    val fromFunctiontoNote: (Int) -> Unit = { noteId ->
        navController.navigate(route = "note/$noteId")
    }

}
