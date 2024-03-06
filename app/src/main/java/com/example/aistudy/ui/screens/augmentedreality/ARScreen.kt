package com.example.aistudy.ui.screens.augmentedreality

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.aistudy.ui.viewmodels.SharedViewModel
import androidx.compose.material3.Text
import com.example.aistudy.utils.Action

@Composable
fun ARScreen(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    Column { // Assuming you want to display it within a Column
        Text(text = "Placeholder: AR functionality coming soon")
    }
}
