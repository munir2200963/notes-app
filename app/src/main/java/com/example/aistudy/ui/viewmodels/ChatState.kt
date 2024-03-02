package com.example.aistudy.ui.viewmodels

import android.graphics.Bitmap
import com.example.aistudy.data.models.Chat

data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)