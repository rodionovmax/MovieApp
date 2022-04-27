package com.gb.movieapp.repository

import com.gb.movieapp.model.ChangeFavoritesDTO
import com.gb.movieapp.model.FavoritesListDTO
import retrofit2.Callback

interface FavoritesRepository {
    fun getFavoritesListFromServer(
        callback: Callback<FavoritesListDTO>
    )

    fun addToFavoritesServer(
        movieId: Int,
        addedFlag: Boolean,
        callback: Callback<ChangeFavoritesDTO>
    )
}