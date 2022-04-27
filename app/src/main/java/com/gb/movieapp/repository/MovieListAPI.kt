package com.gb.movieapp.repository

import com.gb.movieapp.model.MovieListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListAPI {
    @GET("3/movie/popular")
    fun getPopular(
        @Query("api_key") apiKey: String
    ) : Call<MovieListDTO>

    @GET("3/movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String
    ) : Call<MovieListDTO>

    @GET("3/movie/upcoming")
    fun getUpcoming(
        @Query("api_key") apiKey: String
    ) : Call<MovieListDTO>

    @GET("3/movie/top_rated")
    fun getTopRated(
        @Query("api_key") apiKey: String
    ) : Call<MovieListDTO>
}


