package com.gb.movieapp.repository

import androidx.lifecycle.LiveData
import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.model.MovieListDTO
import com.gb.movieapp.viewmodel.AppState
import retrofit2.Callback

interface MovieRepository {
    fun getMovieDetailsFromServer(
        movieId: Int,
        callback: Callback<MovieDetailsDTO>
    )

    fun getMoviesListFromServer(
        sectionId: Int,
        callback: Callback<MovieListDTO>
    )

    fun getPopularSection() : LiveData<AppState>

    fun getNowPLayingSection() : LiveData<AppState>

    fun getUpcomingSection() : LiveData<AppState>

    fun getTopRatedSection() : LiveData<AppState>
}

