package com.gb.movieapp.model

interface Repository {
    fun getMovieDetailsFromServer(): MovieDetails
    fun getMoviesFromLocaleStorage(): List<Movies>
}