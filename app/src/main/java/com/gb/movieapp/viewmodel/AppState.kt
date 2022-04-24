package com.gb.movieapp.viewmodel

import com.gb.movieapp.model.Movie

sealed class AppState {
    data class Success(val success: Any) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}