package com.example.aistudy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aistudy.utils.Constants.CAT_TABLE

@Entity(tableName = CAT_TABLE)
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val emoji: String
)

