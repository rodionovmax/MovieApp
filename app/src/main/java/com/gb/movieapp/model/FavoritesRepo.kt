package com.gb.movieapp.model

interface FavoritesRepo {
    fun getFavoritesFromLocalStorage() : List<Favorites>
    fun addToFavorites()
}