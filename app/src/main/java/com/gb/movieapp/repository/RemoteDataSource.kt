package com.gb.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gb.movieapp.BuildConfig
import com.gb.movieapp.model.ChangeFavoritesDTO
import com.gb.movieapp.model.FavoritesListDTO
import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.model.MovieListDTO
import com.gb.movieapp.model.request.FavoritesPostModel
import com.gb.movieapp.utils.convertDtoToModel
import com.gb.movieapp.viewmodel.AppState
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"


class RemoteDataSource {

    // Movie api
    private val movieAPI = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(MovieAPI::class.java)

    fun getMovieDetails(movieId: Int, callback: Callback<MovieDetailsDTO>) {
        movieAPI.getMovieDetails(movieId, BuildConfig.TMDB_API_KEY).enqueue(callback)
    }

    private fun checkResponse(serverResponse: MovieListDTO): AppState {
        return if (serverResponse.results == null) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(convertDtoToModel(serverResponse))
        }
    }

    fun checkServerError(response : MovieListDTO?) : AppState {
        return if (response != null) {
            checkResponse(response)
        } else {
            AppState.Error(Throwable(SERVER_ERROR))
        }
    }

    fun getPopularList() : LiveData<AppState> {
        val data = MutableLiveData<AppState>()
        GlobalScope.launch(Dispatchers.IO) {
            val response = movieAPI.getPopular(BuildConfig.TMDB_API_KEY)
            data.postValue(checkServerError(response))
        }
        return data
    }

    fun getNowPlayingList() : LiveData<AppState> {
        val data = MutableLiveData<AppState>()
        GlobalScope.launch(Dispatchers.IO) {
            val response = movieAPI.getNowPlaying(BuildConfig.TMDB_API_KEY)
            data.postValue(checkServerError(response))
        }
        return data
    }

    fun getNowUpcomingList() : LiveData<AppState> {
        val data = MutableLiveData<AppState>()
        GlobalScope.launch(Dispatchers.IO) {
            val response = movieAPI.getUpcoming(BuildConfig.TMDB_API_KEY)
            data.postValue(checkServerError(response))
        }
        return data
    }

    fun getTopRatedList() : LiveData<AppState> {
        val data = MutableLiveData<AppState>()
        GlobalScope.launch(Dispatchers.IO) {
            val response = movieAPI.getTopRated(BuildConfig.TMDB_API_KEY)
            data.postValue(checkServerError(response))
        }
        return data
    }

    fun getFavoritesList(callback: Callback<FavoritesListDTO>) {
        movieAPI.getFavoritesList(
            BuildConfig.TMDB_ACCOUNT_ID, BuildConfig.TMDB_API_KEY, BuildConfig.TMDB_SESSION_ID
        ).enqueue(callback)
    }

    fun addToFavorites(movieId: Int, addedFlag: Boolean, callback: Callback<ChangeFavoritesDTO>) {
        movieAPI.addToFavorites(
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