package com.example.aistudy.ui.screens.Speech2TextScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.aistudy.ui.viewmodels.SharedViewModel
import androidx.compose.material3.Text

@Composable
fun Speech2TextScreen(
    navigateToNoteScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    Column { // Assuming you want to display it within a Column
        Text(text = "Placeholder: Speech to Text functionality coming soon")
    }
}
