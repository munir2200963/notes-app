package com.example.aistudy.data.models

import androidx.compose.ui.graphics.Color
import com.example.aistudy.ui.theme.HighPriorityColor
import com.example.aistudy.ui.theme.LowPriorityColor
import com.example.aistudy.ui.theme.MediumPriorityColor
import com.example.aistudy.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}