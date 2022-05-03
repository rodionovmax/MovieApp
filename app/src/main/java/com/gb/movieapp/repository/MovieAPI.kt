package com.gb.movieapp.repository

import com.gb.movieapp.model.ChangeFavoritesDTO
import com.gb.movieapp.model.FavoritesListDTO
import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.model.MovieListDTO
import com.gb.movieapp.model.request.FavoritesPostModel
import retrofit2.Call
import retrofit2.http.*

interface MovieAPI {
    // APIs for movies
    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String
    ) : MovieListDTO

    @GET("3/movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String
    ) : MovieListDTO

    @GET("3/movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String
    ) : MovieListDTO

    @GET("3/movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String
    ) : MovieListDTO

    // APIs for favorites
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

    // API for movie details
    @GET("3/movie/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ) : Call<MovieDetailsDTO>
}