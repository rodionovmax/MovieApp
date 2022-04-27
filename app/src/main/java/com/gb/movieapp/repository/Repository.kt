package com.gb.movieapp.repository

import androidx.lifecycle.MutableLiveData
import com.gb.movieapp.model.Movie
import com.gb.movieapp.viewmodel.AppState

// TODO: Can be deleted
interface Repository {
    fun getMovieDetailsFromServer(): Movie
    fun getMovieListFromLocaleStorage(): List<Movie>
    fun getFavoritesListFromLocaleStorage(): List<Movie>
    fun addMovieToFavorites(movieId : Int, addedFlag : Boolean, sessionId : String) : MutableLiveData<AppState>
    fun removeMovieFromFavorites(movieId : Int, addedFlag : Boolean, sessionId : String) : MutableLiveData<AppState>
    fun getMovieListFromServer(sectionId: Int): MutableLiveData<AppState>
    fun getFavoritesListFromServer(): MutableLiveData<AppState>

}