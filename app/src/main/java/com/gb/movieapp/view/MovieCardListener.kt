package com.gb.movieapp.view

import android.view.View
import com.gb.movieapp.model.Favorites
import com.gb.movieapp.model.Movie

interface MovieCardListener {
    fun onMovieCardClicked(movie: Movie)
}

interface OnFavoritesCheckboxListener {
    fun onItemChecked(p0: View, movie: Movie)
}

interface UpdateFavoritesListener {
    fun updateFavorites(movieId : Int, addedFlag : Boolean)
}