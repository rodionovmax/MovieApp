package com.gb.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.movieapp.model.FavoritesListDTO
import com.gb.movieapp.repository.FavoritesRepository
import com.gb.movieapp.repository.FavoritesRepositoryImpl
import com.gb.movieapp.repository.MovieRepositoryImpl
import com.gb.movieapp.repository.RemoteDataSource
import com.gb.movieapp.utils.convertFavoritesDtoToModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class FavoritesListViewModel(
    val favoritesListLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val favoritesListRepositoryImpl: FavoritesRepository = FavoritesRepositoryImpl(
        RemoteDataSource()
    )
) : ViewModel() {
    fun getLiveData() = favoritesListLiveData

    fun getFavoritesListFromRemoteSource() {
        favoritesListLiveData.value = AppState.Loading
        favoritesListRepositoryImpl.getFavoritesListFromServer(callback)
    }

    private val callback = object : Callback<FavoritesListDTO> {
        override fun onResponse(
            call: Call<FavoritesListDTO>,
            response: Response<FavoritesListDTO>
        ) {
            val serverResponse : FavoritesListDTO? = response.body()
            favoritesListLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<FavoritesListDTO>, t: Throwable) {
            favoritesListLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    private fun checkResponse(serverResponse: FavoritesListDTO): AppState {
        return if (serverResponse.results == null) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(convertFavoritesDtoToModel(serverResponse))
        }
    }
}


