package com.gb.movieapp.repository

import androidx.lifecycle.LiveData
import com.gb.movieapp.model.ChangeFavoritesDTO
import com.gb.movieapp.model.FavoritesListDTO
import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.model.MovieListDTO
import com.gb.movieapp.viewmodel.AppState
import retrofit2.Callback


class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource) : MovieRepository {
    override fun getMovieDetailsFromServer(movieId: Int, callback: retrofit2.Callback<MovieDetailsDTO>) {
        remoteDataSource.getMovieDetails(movieId, callback)
    }

    override fun getPopularSection(): LiveData<AppState> {
        return remoteDataSource.getPopularList()
    }

    override fun getNowPLayingSection(): LiveData<AppState> {
        return remoteDataSource.getNowPlayingList()
    }

    override fun getUpcomingSection(): LiveData<AppState> {
        return remoteDataSource.getNowUpcomingList()
    }

    override fun getTopRatedSection(): LiveData<AppState> {
        return remoteDataSource.getTopRatedList()
    }

    override fun getFavoritesListFromServer(callback: Callback<FavoritesListDTO>) {
        remoteDataSource.getFavoritesList(callback)
    }

    override fun addToFavoritesServer(
        movieId: Int,
        addedFlag: Boolean,
        callback: Callback<ChangeFavoritesDTO>
    ) {
        remoteDataSource.addToFavorites(movieId, addedFlag, callback)
    }

}