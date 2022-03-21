package com.gb.movieapp.model

interface Repository {
    fun getMovieDetailsFromServer(): Movie
    fun getMovieListFromLocaleStorage(): List<Movie>
    fun getFavoritesListFromLocaleStorage(): List<Movie>
    fun addMovieToFavorites(): Movie
}