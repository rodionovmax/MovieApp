package com.gb.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.movieapp.model.getMovieReviewsLocal

class HistoryViewModel(
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
//    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())  // TODO: Uncomment when the database will be created
) : ViewModel() {

    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        historyLiveData.value = AppState.Success(getMovieReviewsLocal())
    }
}