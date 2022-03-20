package com.gb.movieapp.viewmodel

import com.gb.movieapp.model.Favorites
import com.gb.movieapp.model.Movies

sealed class AppState {
    data class Success(val favoriteMovies: List<Favorites>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}