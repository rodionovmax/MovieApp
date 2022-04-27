package com.gb.movieapp.repository

import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.model.MovieListDTO
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
}