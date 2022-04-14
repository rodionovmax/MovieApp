package com.gb.movieapp.model

import androidx.lifecycle.MutableLiveData
import com.gb.movieapp.viewmodel.AppState

interface Repository {
    fun getMovieDetailsFromServer(): Movie
    fun getMovieListFromLocaleStorage(): List<Movie>
    fun getFavoritesListFromLocaleStorage(): List<Movie>
    fun addMovieToFavorites(movie: Movie)
    fun getMovieListFromServer(sectionId: Int): MutableLiveData<AppState>
    fun getFavoritesListFromServer(): MutableLiveData<AppState>

}