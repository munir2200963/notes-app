package com.example.aistudy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aistudy.utils.Constants.DATABASE_TABLE
import java.util.*

@Entity(tableName = DATABASE_TABLE)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
    val reminderDateTime: Date?,
    val workerRequestId: UUID?,
    val createdAt: Date,
    val updatedAt: Date,
)


