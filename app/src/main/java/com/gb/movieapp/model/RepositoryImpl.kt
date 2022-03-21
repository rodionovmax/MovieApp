package com.gb.movieapp.model

class RepositoryImpl : Repository {
    override fun getMovieDetailsFromServer(): Movie {
        TODO("Not yet implemented")
    }

    override fun getMovieListFromLocaleStorage(): List<Movie> {
        return getMoviesList()
    }

    override fun getFavoritesListFromLocaleStorage(): List<Movie> {
        return getFavoritesList()
    }

    override fun addMovieToFavorites(): Movie {
        TODO("Not yet implemented")
    }
}