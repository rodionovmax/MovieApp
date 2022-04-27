package com.gb.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.movieapp.model.ChangeFavoritesDTO
import com.gb.movieapp.repository.FavoritesRepository
import com.gb.movieapp.repository.FavoritesRepositoryImpl
import com.gb.movieapp.repository.RemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class AddToFavoritesViewModel(
    val addToFavoritesLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val favoritesRepositoryImpl: FavoritesRepository = FavoritesRepositoryImpl(
        RemoteDataSource()
    )
) : ViewModel() {
    fun getLiveData() = addToFavoritesLiveData

    fun markAsFavorite(movieId: Int, addedFlag: Boolean) {
        addToFavoritesLiveData.value = AppState.Loading
        favoritesRepositoryImpl.addToFavoritesServer(movieId, addedFlag, callback)
    }

    private val callback = object : Callback<ChangeFavoritesDTO> {
        override fun onResponse(call: Call<ChangeFavoritesDTO>, response: Response<ChangeFavoritesDTO>) {
            val serverResponse : ChangeFavoritesDTO? = response.body()
            addToFavoritesLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<ChangeFavoritesDTO>, t: Throwable) {
            addToFavoritesLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

    }

    private fun checkResponse(serverResponse: ChangeFavoritesDTO): AppState {
        return if (serverResponse.success == null) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(serverResponse)
        }
    }
}
