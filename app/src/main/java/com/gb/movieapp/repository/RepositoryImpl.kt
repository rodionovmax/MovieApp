package com.gb.movieapp.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.gb.movieapp.BuildConfig
import com.gb.movieapp.model.*
import com.gb.movieapp.model.request.FavoritesPostModel
import com.gb.movieapp.view.favorites.FavoritesListLoader
import com.gb.movieapp.view.home.AddToFavoritesLoader
import com.gb.movieapp.view.home.MoviesListLoader
import com.gb.movieapp.viewmodel.AppState


// TODO: Can be deleted
class RepositoryImpl : Repository {

    val sCustomTag = "My Custom Tag"

    override fun getMovieDetailsFromServer(): Movie {
        TODO("Not yet implemented")
    }

    override fun getMovieListFromLocaleStorage() = getMoviesList()

    override fun getFavoritesListFromLocaleStorage() = getFavoritesList()

    private val favorites: ArrayList<Movie> =
        getFavoritesListFromLocaleStorage() as ArrayList<Movie>

    @RequiresApi(Build.VERSION_CODES.N)
    override fun addMovieToFavorites(
        movieId: Int,
        addedFlag: Boolean,
        sessionId: String
    ): MutableLiveData<AppState> {
        val data = MutableLiveData<AppState>()
        data.postValue(AppState.Loading)
        val onLoadListener: AddToFavoritesLoader.AddToFavoritesLoaderListener =
            object : AddToFavoritesLoader.AddToFavoritesLoaderListener {
                override fun onLoaded(addedToFavorites: ChangeFavoritesDTO) {
                    data.postValue(AppState.Success(addedToFavorites.success))
                }

                override fun onFailed(throwable: Throwable) {
                    Log.d(
                        sCustomTag,
                        "addMovieToFavorites onFailed() called with: throwable = $throwable"
                    )
                }
            }
        val loader = AddToFavoritesLoader(onLoadListener, sessionId)
        loader.addToFavorites(
            FavoritesPostModel(
                mediaType = "movie",
                mediaId = movieId,
                favorite = addedFlag
            )
        )
        return data
    }

    override fun removeMovieFromFavorites(
        movieId: Int,
        addedFlag: Boolean,
        sessionId: String
    ): MutableLiveData<AppState> {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMovieListFromServer(sectionId: Int): MutableLiveData<AppState> {
        val data = MutableLiveData<AppState>()
        data.postValue(AppState.Loading)
        val onLoadListener: MoviesListLoader.MoviesLoaderListener =
            object : MoviesListLoader.MoviesLoaderListener {
                override fun onLoaded(moviesDTO: MovieListDTO) {
                    val movies = moviesDTO.results.map {
                        Movie(
                            id = it.id,
                            originalTitle = it.originalTitle,
                            posterUrl = it.posterPath,
                            releaseYear = it.releaseDate.split("-")[0],
                            rating = it.voteAverage,
                        )
                    }
                    data.postValue(AppState.Success(movies))
                }

                override fun onFailed(throwable: Throwable) {
                    Log.d(
                        sCustomTag,
                        "getMovieListFromServer onFailed() called with: throwable = $throwable"
                    )
                }
            }
        val loader = MoviesListLoader(onLoadListener, sectionId, BuildConfig.TMDB_API_KEY)
        loader.loadMoviesSectionList()
        return data
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getFavoritesListFromServer(): MutableLiveData<AppState> {
        val data = MutableLiveData<AppState>()
        val onLoadListener: FavoritesListLoader.FavoritesLoaderListener =
            object : FavoritesListLoader.FavoritesLoaderListener {
                override fun onLoaded(favoritesDTO: FavoritesListDTO) {
                    val favorites = favoritesDTO.results.map {
                        Movie(
                            id = it.id,
                            originalTitle = it.originalTitle,
                            genres = it.genres,
                            genreIds = it.genreIds,
                            posterUrl = it.posterPath,
                            releaseDate = it.releaseDate,
                            rating = it.voteAverage,
                            releaseYear = it.releaseDate.split("-")[0]
                        )
                    }
                    data.postValue(AppState.Success(favorites))
                }

                override fun onFailed(throwable: Throwable) {
                    Log.d(
                        sCustomTag,
                        "getFavoritesListFromServer onFailed() called with: throwable = $throwable"
                    )
                }
            }
        val loader = FavoritesListLoader(
            onLoadListener,
            BuildConfig.TMDB_ACCOUNT_ID,
            BuildConfig.TMDB_API_KEY,
            BuildConfig.TMDB_SESSION_ID
        )
        loader.loadFavorites()
        return data
    }

}