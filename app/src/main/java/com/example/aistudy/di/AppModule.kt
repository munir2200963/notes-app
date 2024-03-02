package com.example.aistudy.di

import android.content.Context
import androidx.room.Room
import com.example.aistudy.data.NotesDatabase
import com.example.aistudy.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NotesDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNotesDao(database: NotesDatabase) = database.notesDao()

}