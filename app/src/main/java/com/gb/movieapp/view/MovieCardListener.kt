package com.gb.movieapp.view

import android.view.View
import com.gb.movieapp.model.Movie

interface MovieCardListener {
    fun onMovieCardClicked(movie: Movie)
}

interface OnFavoritesCheckboxListener {
    fun onItemChecked(p0: View, movie: Movie)
}