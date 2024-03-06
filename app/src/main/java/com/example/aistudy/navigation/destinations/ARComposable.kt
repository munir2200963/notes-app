package com.example.aistudy.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.aistudy.ui.screens.augmentedreality.ARScreen
import com.example.aistudy.ui.viewmodels.SharedViewModel
import com.example.aistudy.utils.Action
import com.example.aistudy.utils.Constants.AR_SCREEN

fun NavGraphBuilder.ARComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(route = AR_SCREEN) {
        ARScreen(
            navigateToListScreen = navigateToListScreen,
            sharedViewModel = sharedViewModel
        )
    }
}
