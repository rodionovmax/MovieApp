package com.gb.movieapp.repository

import com.gb.movieapp.BuildConfig
import com.gb.movieapp.model.ChangeFavoritesDTO
import com.gb.movieapp.model.FavoritesListDTO
import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.model.MovieListDTO
import com.gb.movieapp.model.request.FavoritesPostModel
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    // Movie Details
    private val movieDetailsAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(MovieDetailsAPI::class.java)

    fun getMovieDetails(movieId: Int, callback: Callback<MovieDetailsDTO>) {
        movieDetailsAPI.getMovieDetails(movieId, BuildConfig.TMDB_API_KEY).enqueue(callback)
    }

    // List Movies
    private val movieListAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(MovieListAPI::class.java)

    fun getMovieList(sectionId: Int, callback: Callback<MovieListDTO>) {
        when (sectionId) {
            0 -> movieListAPI.getPopular(BuildConfig.TMDB_API_KEY).enqueue(callback)
            1 -> movieListAPI.getNowPlaying(BuildConfig.TMDB_API_KEY).enqueue(callback)
            2 -> movieListAPI.getUpcoming(BuildConfig.TMDB_API_KEY).enqueue(callback)
            3 -> movieListAPI.getTopRated(BuildConfig.TMDB_API_KEY).enqueue(callback)
        }
    }

    // List Favorites
    private val favoritesListAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(FavoritesAPI::class.java)

    fun getFavoritesList(callback: Callback<FavoritesListDTO>) {
        favoritesListAPI.getFavoritesList(
            BuildConfig.TMDB_ACCOUNT_ID, BuildConfig.TMDB_API_KEY, BuildConfig.TMDB_SESSION_ID
        ).enqueue(callback)
    }

    // Mark as favorite
    private val addToFavoritesAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(FavoritesAPI::class.java)

    fun addToFavorites(movieId: Int, addedFlag: Boolean, callback: Callback<ChangeFavoritesDTO>) {
        addToFavoritesAPI.addToFavorites(
            BuildConfig.TMDB_ACCOUNT_ID,
            BuildConfig.TMDB_API_KEY,
            BuildConfig.TMDB_SESSION_ID,
            FavoritesPostModel(
                mediaType = "movie",
                mediaId = movieId,
                favorite = addedFlag
            )
        ).enqueue(callback)
    }
}