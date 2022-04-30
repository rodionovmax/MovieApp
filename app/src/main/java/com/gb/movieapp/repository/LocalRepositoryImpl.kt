package com.gb.movieapp.repository

import com.gb.movieapp.model.MovieReview
import com.gb.movieapp.model.room.ReviewDao
import com.gb.movieapp.utils.convertReviewEntityToMovieReview
import com.gb.movieapp.utils.convertReviewToEntity

class LocalRepositoryImpl(private val localDataSource: ReviewDao) : LocalRepository {
    override fun getAllReviews(): List<MovieReview> {
        return convertReviewEntityToMovieReview(localDataSource.all())
    }

    override fun saveEntity(review: MovieReview) {
        localDataSource.insert(convertReviewToEntity(review))
    }

}