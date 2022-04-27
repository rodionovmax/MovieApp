package com.gb.movieapp.repository

import com.gb.movieapp.model.ChangeFavoritesDTO
import com.gb.movieapp.model.FavoritesListDTO
import com.gb.movieapp.model.request.FavoritesPostModel
import retrofit2.Call
import retrofit2.http.*

interface FavoritesAPI {
    @GET("3/account/{account_id}/favorite/movies")
    fun getFavoritesList(
        @Path("account_id") accountId: String,
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
    ) : Call<FavoritesListDTO>

    @POST("3/account/{account_id}/favorite")
    fun addToFavorites(
        @Path("account_id") accountId: String,
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String,
        @Body rawData: FavoritesPostModel
    ) : Call<ChangeFavoritesDTO>


}
