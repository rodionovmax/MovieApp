package com.gb.movieapp.view

import com.gb.movieapp.model.Favorites

interface MovieCard {
    fun onMovieCardClicked(favorites: Favorites)
}