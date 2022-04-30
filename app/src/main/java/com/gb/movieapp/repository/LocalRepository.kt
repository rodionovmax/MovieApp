package com.gb.movieapp.repository

import com.gb.movieapp.model.MovieReview

interface LocalRepository {
    fun getAllReviews() : List<MovieReview>
    fun saveEntity(review: MovieReview)
}