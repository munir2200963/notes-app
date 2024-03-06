package com.example.aistudy.ui.screens.Image2Text

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.aistudy.ui.viewmodels.SharedViewModel
import androidx.compose.material3.Text

@Composable
fun Image2TextScreen(
    navigateToNoteScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    Column { // Assuming you want to display it within a Column
        Text(text = "Placeholder: Image to Text functionality coming soon")
    }
}
