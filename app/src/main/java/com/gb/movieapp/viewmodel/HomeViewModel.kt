package com.gb.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.movieapp.model.Movies
import com.gb.movieapp.model.Repository
import com.gb.movieapp.model.RepositoryImpl
import com.gb.movieapp.model.getMovies

class HomeViewModel(
    private val liveDataToObserve : MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) : ViewModel() {

//    // Get list of movies
//    private var _moviesList = MutableLiveData<List<Movies>>()
//    val moviesList: LiveData<List<Movies>>
//        get() = _moviesList

    fun getLiveData() = liveDataToObserve

    fun getMoviesFromLocal() = getMovies()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
//        Thread {
//            Thread.sleep(1000)
//            liveDataToObserve.postValue(AppState.Success(if (isRussian) repositoryImpl.getWeatherFromLocalStorageRus() else repositoryImpl.getWeatherFromLocalStorageWorld()))
//        }.start()
    }
}

