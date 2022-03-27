package com.gb.movieapp.model

class RepositoryImpl : Repository {

    override fun getMovieDetailsFromServer(): Movie {
        TODO("Not yet implemented")
    }

    override fun getMovieListFromLocaleStorage() = getMoviesList()

    override fun getFavoritesListFromLocaleStorage() = getFavoritesList()

    private val favorites: ArrayList<Movie> =
        getFavoritesListFromLocaleStorage() as ArrayList<Movie>

    override fun addMovieToFavorites(movie: Movie) {
        favorites.add(movie)
    }

}