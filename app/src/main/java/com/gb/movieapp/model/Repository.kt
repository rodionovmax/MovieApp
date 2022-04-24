package com.gb.movieapp.model

import androidx.lifecycle.MutableLiveData
import com.gb.movieapp.viewmodel.AppState

interface Repository {
    fun getMovieDetailsFromServer(): Movie
    fun getMovieListFromLocaleStorage(): List<Movie>
    fun getFavoritesListFromLocaleStorage(): List<Movie>
    fun addMovieToFavorites(movieId : Int, addedFlag : Boolean, sessionId : String) : MutableLiveData<AppState>
    fun getMovieListFromServer(sectionId: Int): MutableLiveData<AppState>
    fun getFavoritesListFromServer(): MutableLiveData<AppState>

}