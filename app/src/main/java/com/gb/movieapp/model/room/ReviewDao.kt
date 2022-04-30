package com.gb.movieapp.model.room

import androidx.room.*

interface ReviewDao {

    @Query("SELECT * FROM ReviewEntity")
    fun all() : List<ReviewEntity>

    @Query("SELECT * FROM ReviewEntity WHERE title LIKE :title")
    fun getDataByWord(title: String): List<ReviewEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: ReviewEntity)

    @Update
    fun update(entity: ReviewEntity)

    @Delete
    fun delete(entity: ReviewEntity)
}