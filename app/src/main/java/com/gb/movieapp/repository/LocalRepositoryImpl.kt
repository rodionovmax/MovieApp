package com.gb.movieapp.repository

import android.content.Context
import com.gb.movieapp.BuildConfig
import com.gb.movieapp.model.MovieReview
import com.gb.movieapp.model.room.ReviewDao
import com.gb.movieapp.utils.convertReviewEntityToMovieReview
import com.gb.movieapp.utils.convertReviewToEntity
import kotlinx.coroutines.*

class LocalRepositoryImpl(private val localDataSource: ReviewDao) : LocalRepository {
    override fun getAllReviews(): List<MovieReview> {
        var listMovieReviews = listOf<MovieReview>()
        runBlocking {
            launch(Dispatchers.Default) {
                // default context
                withContext(Dispatchers.IO) {
                    // IO context
                    listMovieReviews = convertReviewEntityToMovieReview(localDataSource.all())
                }
                // back to default context
            }
        }
        return listMovieReviews
    }

    override fun saveEntity(review: MovieReview) {
        GlobalScope.launch(Dispatchers.IO) {
            localDataSource.insert(convertReviewToEntity(review))
        }
    }

}