package com.gb.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.movieapp.model.MovieListDTO
import com.gb.movieapp.repository.MovieRepository
import com.gb.movieapp.repository.MovieRepositoryImpl
import com.gb.movieapp.repository.RemoteDataSource
import com.gb.movieapp.utils.convertDtoToModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class MovieListViewModel(
    val movieListLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val movieListRepositoryImpl: MovieRepository = MovieRepositoryImpl(RemoteDataSource())
) : ViewModel() {
    fun getLiveData() = movieListLiveData

    fun getMovieListFromRemoteSource(sectionId: Int) {
        movieListLiveData.value = AppState.Loading
        movieListRepositoryImpl.getMoviesListFromServer(sectionId, callback)
    }

    private val callback = object : Callback<MovieListDTO> {
        override fun onResponse(call: Call<MovieListDTO>, response: Response<MovieListDTO>) {
            val serverResponse : MovieListDTO? = response.body()
            movieListLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MovieListDTO>, t: Throwable) {
            movieListLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

    }

    private fun checkResponse(serverResponse: MovieListDTO): AppState {
        return if (serverResponse.results == null) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(convertDtoToModel(serverResponse))
        }
    }
}

