package com.gb.movieapp.utils

import androidx.lifecycle.Transformations.map
import com.gb.movieapp.model.*

const val PICTURE_BASE_URL = "https://image.tmdb.org/t/p/original"


fun convertDtoToModel(movieListDTO: MovieListDTO) : List<Movie> {
    val movies = movieListDTO.results.map {
        Movie(
            id = it.id,
            originalTitle = it.originalTitle,
            posterUrl = it.posterPath,
            releaseYear = it.releaseDate.split("-")[0],
            rating = it.voteAverage,
        )
    }
    return movies
}

fun convertFavoritesDtoToModel(favoritesListDTO: FavoritesListDTO) : List<Movie> {
    val favorites = favoritesListDTO.results.map {
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
    return favorites
}



