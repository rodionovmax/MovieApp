package com.gb.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.movieapp.model.Movie
import com.gb.movieapp.model.Repository
import com.gb.movieapp.model.RepositoryImpl

class MyViewModel(
    private var liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repoImpl: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getMoviesFromLocal() = getDataFromLocalSource()

    fun getMoviesFromServer(sectionId: Int) = getDataFromServerSource(sectionId)

    private fun getDataFromServerSource(sectionId: Int): MutableLiveData<AppState> {
        liveDataToObserve.value = AppState.Loading
        return repoImpl.getMovieListFromServer(sectionId)
    }

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repoImpl.getMovieListFromLocaleStorage()))
        }.start()
    }

    fun markAsFavorite(movieId : Int, addedFlag : Boolean, sessionId: String): MutableLiveData<AppState> {
        liveDataToObserve.value = AppState.Loading
        return repoImpl.addMovieToFavorites(movieId, addedFlag, sessionId)
    }

    fun getFavoritesFromLocal() = getFavoritesFromLocalStorage()

    fun getFavoritesFromServer() : MutableLiveData<AppState> {
        liveDataToObserve.value = AppState.Loading
        return repoImpl.getFavoritesListFromServer()
    }

    private fun getFavoritesFromLocalStorage() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repoImpl.getFavoritesListFromLocaleStorage()))
        }.start()
    }

}

