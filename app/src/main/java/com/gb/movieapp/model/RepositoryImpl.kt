package com.gb.movieapp.model

class RepositoryImpl : Repository {
    override fun getMovieDetailsFromServer(): MovieDetails {
        TODO("Not yet implemented")
    }

    override fun getMoviesFromLocaleStorage(): List<Movies> {
        return getMovies()
    }
}