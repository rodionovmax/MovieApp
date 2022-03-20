package com.gb.movieapp.model

class RepositoryImpl : Repository {
    override fun getMovieDetailsFromServer(): Movie {
        TODO("Not yet implemented")
    }

    override fun getMoviesFromLocaleStorage(): List<Movies> {
        return getMovies()
    }

    override fun getFavoritesFromLocalStorage() : List<Favorites> {
        return getFavoriteMovies()
    }
}