package com.gb.movieapp.model.room

import androidx.room.*

@Dao
interface ReviewDao {

    @Query("SELECT * FROM ReviewEntity")
    fun all() : List<ReviewEntity>

    @Query("SELECT * FROM ReviewEntity WHERE title LIKE :title")
    fun getDataByWord(title: String): List<ReviewEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)  // TODO: read about conflict strategies
    suspend fun insert(entity: ReviewEntity)

    @Update
    fun update(entity: ReviewEntity)

    @Delete
    fun delete(entity: ReviewEntity)
}