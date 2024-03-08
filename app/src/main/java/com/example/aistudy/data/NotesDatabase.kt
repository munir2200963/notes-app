package com.example.aistudy.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aistudy.data.models.Note
import com.example.aistudy.utils.Converters
import com.example.aistudy.data.models.Category

@Database(entities = [Note::class, Category::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
    abstract fun categoryDao(): CategoryDao
}