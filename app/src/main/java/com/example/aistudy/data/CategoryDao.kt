package com.example.aistudy.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aistudy.data.models.Category
import com.example.aistudy.data.models.Note
import com.example.aistudy.utils.Constants
import com.example.aistudy.utils.Constants.CAT_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //replace to prevent duplicate cat
    suspend fun addCategory(category: Category)

    @Query("SELECT * FROM $CAT_TABLE")
    fun getAllCategories(): Flow<List<Category>>

    @Query("DELETE FROM $CAT_TABLE")
    suspend fun deleteAllCategories()

}