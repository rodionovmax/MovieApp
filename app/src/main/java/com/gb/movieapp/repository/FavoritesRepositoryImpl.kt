package com.gb.movieapp.repository

import com.gb.movieapp.model.ChangeFavoritesDTO
import com.gb.movieapp.model.FavoritesListDTO
import retrofit2.Callback

class FavoritesRepositoryImpl(private val remoteDataSource: RemoteDataSource) : FavoritesRepository {
    override fun getFavoritesListFromServer(callback: Callback<FavoritesListDTO>) {
        remoteDataSource.getFavoritesList(callback)
    }

    override fun addToFavoritesServer(
        movieId: Int,
        addedFlag: Boolean,
        callback: Callback<ChangeFavoritesDTO>
    ) {
        remoteDataSource.addToFavorites(movieId, addedFlag, callback)
    }
}
