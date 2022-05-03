package com.gb.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.movieapp.App.Companion.getReviewsDao
import com.gb.movieapp.model.MovieReview
import com.gb.movieapp.model.getMovieReviewsLocal
import com.gb.movieapp.repository.LocalRepository
import com.gb.movieapp.repository.LocalRepositoryImpl

class ReviewViewModel(
    val reviewLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val reviewsRepository: LocalRepository = LocalRepositoryImpl(getReviewsDao())  // TODO: Uncomment when the database will be created
) : ViewModel() {

    fun getAllReviews() {
        reviewLiveData.value = AppState.Loading
//        reviewLiveData.value = AppState.Success(getMovieReviewsLocal())  // Taking reviews from hardcoded function TODO: delete
        reviewLiveData.value = AppState.Success(reviewsRepository.getAllReviews())  // Uncomment when reviews will be saving to local repository
    }

    fun addReview(review : MovieReview) {
        reviewsRepository.saveEntity(review)
    }
}