package com.gb.movieapp.viewmodel

import androidx.lifecycle.LiveData
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

class MovieListViewModel(
    movieListRepositoryImpl: MovieRepository = MovieRepositoryImpl(RemoteDataSource())
) : ViewModel() {

    private val popularViewModel = movieListRepositoryImpl.getPopularSection()
    private val nowPlayingViewModel = movieListRepositoryImpl.getNowPLayingSection()
    private val upcomingViewModel = movieListRepositoryImpl.getUpcomingSection()
    private val topRatedViewModel = movieListRepositoryImpl.getTopRatedSection()


    fun getPopular() : LiveData<AppState> {
        return popularViewModel
    }

    fun getNowPlaying() : LiveData<AppState> {
        return nowPlayingViewModel
    }

    fun getUpcoming() : LiveData<AppState> {
        return upcomingViewModel
    }

    fun getTopRated() : LiveData<AppState> {
        return topRatedViewModel
    }
}

