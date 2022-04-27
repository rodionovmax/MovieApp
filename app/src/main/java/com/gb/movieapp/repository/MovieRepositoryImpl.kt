package com.gb.movieapp.repository

import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.model.MovieListDTO
import retrofit2.Callback


class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource) : MovieRepository {
    override fun getMovieDetailsFromServer(movieId: Int, callback: retrofit2.Callback<MovieDetailsDTO>) {
        remoteDataSource.getMovieDetails(movieId, callback)
    }

    override fun getMoviesListFromServer(sectionId: Int, callback: Callback<MovieListDTO>) {
        remoteDataSource.getMovieList(sectionId, callback)
    }

}