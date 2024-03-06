package com.example.aistudy.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.aistudy.ui.screens.Speech2TextScreen.Speech2TextScreen
import com.example.aistudy.ui.viewmodels.SharedViewModel
import com.example.aistudy.utils.Constants.SPEECH2TEXT_SCREEN

fun NavGraphBuilder.Speech2TextComposable(
    navigateToNoteScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(route = SPEECH2TEXT_SCREEN) {
        Speech2TextScreen(
            navigateToNoteScreen = navigateToNoteScreen,
            sharedViewModel = sharedViewModel
        )
    }
}