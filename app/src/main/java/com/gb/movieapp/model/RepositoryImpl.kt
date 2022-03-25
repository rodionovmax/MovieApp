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

    private val favorites: ArrayList<Movie> = getFavoritesListFromLocaleStorage() as ArrayList<Movie>

    override fun addMovieToFavorites(movie: Movie) {
        favorites.add(movie)
    }

}