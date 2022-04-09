package com.gb.movieapp.model

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.gb.movieapp.BuildConfig
import com.gb.movieapp.view.home.MoviesListLoader
import com.gb.movieapp.viewmodel.AppState

class RepositoryImpl : Repository {

    override fun getMovieDetailsFromServer(): Movie {
        TODO("Not yet implemented")
    }

    override fun getMovieListFromLocaleStorage() = getMoviesList()

    override fun getFavoritesListFromLocaleStorage() = getFavoritesList()

    private val favorites: ArrayList<Movie> =
        getFavoritesListFromLocaleStorage() as ArrayList<Movie>

    override fun addMovieToFavorites(movie: Movie) {
        favorites.add(movie)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMovieListFromServer(sectionId: Int): MutableLiveData<AppState> {
        val data = MutableLiveData<AppState>()
        val onLoadListener: MoviesListLoader.MoviesLoaderListener =
            object : MoviesListLoader.MoviesLoaderListener {
                override fun onLoaded(moviesDTO: MovieListDTO) {
                    val movies = moviesDTO.results.map {
                        Movie(
                            id = it.id,
                            originalTitle = it.original_title,
                            posterUrl = it.poster_path,
                            releaseDate = it.release_date,
                            rating = it.vote_average
                        )
                    }
                    data.postValue(AppState.Success(movies))
                }

                override fun onFailed(throwable: Throwable) {
                    Log.d(TAG, "onFailed() called with: throwable = $throwable")
                }
            }
        val loader = MoviesListLoader(onLoadListener, sectionId, BuildConfig.TMDB_API_KEY)
        loader.loadMoviesSectionList()
        return data
    }

}