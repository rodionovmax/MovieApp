package com.gb.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.movieapp.model.FavoritesRepo
import com.gb.movieapp.model.FavoritesRepoImpl
import com.gb.movieapp.model.getFavoriteMovies

class FavoritesViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val favoritesRepoImpl : FavoritesRepo = FavoritesRepoImpl()
) : ViewModel() {

    fun getLiveData() = liveDataToObserve

//    fun getFavorites() = getFavoriteMovies()
    fun getFavorites() = getFavoritesFromLocal()

    private fun getFavoritesFromLocal() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(AppState.Success(favoritesRepoImpl.getFavoritesFromLocalStorage()))
        }.start()
    }

}