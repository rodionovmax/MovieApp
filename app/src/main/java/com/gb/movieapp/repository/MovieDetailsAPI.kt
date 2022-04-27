package com.gb.movieapp.repository

import com.gb.movieapp.model.MovieDetailsDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsAPI {
    @GET("3/movie/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ) : Call<MovieDetailsDTO>
}