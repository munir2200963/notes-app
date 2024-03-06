package com.example.aistudy.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.aistudy.ui.screens.Image2Text.Image2TextScreen
import com.example.aistudy.ui.viewmodels.SharedViewModel
import com.example.aistudy.utils.Constants.IMAGE2TEXT_SCREEN

fun NavGraphBuilder.Image2TextComposable(
    navigateToNoteScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(route = IMAGE2TEXT_SCREEN) {
        Image2TextScreen(
            navigateToNoteScreen = navigateToNoteScreen,
            sharedViewModel = sharedViewModel
        )
    }
}
