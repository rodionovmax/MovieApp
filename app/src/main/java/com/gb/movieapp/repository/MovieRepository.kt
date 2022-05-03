package com.gb.movieapp.repository

import androidx.lifecycle.LiveData
import com.gb.movieapp.model.ChangeFavoritesDTO
import com.gb.movieapp.model.FavoritesListDTO
import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.model.MovieListDTO
import com.gb.movieapp.viewmodel.AppState
import retrofit2.Callback

interface MovieRepository {
    fun getMovieDetailsFromServer(
        movieId: Int,
        callback: Callback<MovieDetailsDTO>
    )

    fun getPopularSection() : LiveData<AppState>

    fun getNowPLayingSection() : LiveData<AppState>

    fun getUpcomingSection() : LiveData<AppState>

    fun getTopRatedSection() : LiveData<AppState>

    fun getFavoritesListFromServer(
        callback: Callback<FavoritesListDTO>
    )

    fun addToFavoritesServer(
        movieId: Int,
        addedFlag: Boolean,
        callback: Callback<ChangeFavoritesDTO>
    )
}

