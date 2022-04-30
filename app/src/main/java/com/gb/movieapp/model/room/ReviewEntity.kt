package com.gb.movieapp.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val movieId: Int,
    val title: String,
    val review: String? = null
)
