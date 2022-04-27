package com.gb.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.repository.MovieRepository
import com.gb.movieapp.repository.MovieRepositoryImpl
import com.gb.movieapp.repository.RemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class MovieDetailsViewModel(
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: MovieRepository = MovieRepositoryImpl(
        RemoteDataSource()
    )
) : ViewModel() {
    fun getLiveData() = detailsLiveData

    fun getMovieDetailsFromRemoteSource(movieId: Int) {
        detailsLiveData.value = AppState.Loading
        detailsRepositoryImpl.getMovieDetailsFromServer(movieId, callback)
    }

    private val callback = object : Callback<MovieDetailsDTO> {
        override fun onResponse(call: Call<MovieDetailsDTO>, response: Response<MovieDetailsDTO>) {
            val serverResponse: MovieDetailsDTO? = response.body()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MovieDetailsDTO>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: MovieDetailsDTO): AppState {
            return if (serverResponse.budget == null || serverResponse.genres == null ||
                serverResponse.id == null || serverResponse.originalTitle == null ||
                serverResponse.overview == null || serverResponse.posterPath == null ||
                serverResponse.releaseDate == null || serverResponse.revenue == null ||
                serverResponse.runtime == null || serverResponse.title == null ||
                serverResponse.voteAverage == null
            ) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(serverResponse)
            }
        }
    }
}