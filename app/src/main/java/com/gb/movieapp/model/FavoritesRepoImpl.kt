package com.gb.movieapp.model

class FavoritesRepoImpl : FavoritesRepo {
    override fun getFavoritesFromLocalStorage() : List<Favorites> {
        return getFavoriteMovies()
    }

    override fun addToFavorites() {
        TODO("Not yet implemented")
    }

}