package com.gb.movieapp.model

interface Repository {
    fun getMovieDetailsFromServer(): Movie
    fun getMoviesFromLocaleStorage(): List<Movies>
    fun getFavoritesFromLocalStorage(): List<Favorites>
}