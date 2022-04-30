package com.gb.movieapp.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ReviewEntity::class), version = 1, exportSchema = false)
abstract class ReviewDatabase : RoomDatabase() {
    abstract fun reviewsDao() : ReviewDao
}