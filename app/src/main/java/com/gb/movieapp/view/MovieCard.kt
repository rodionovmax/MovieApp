package com.gb.movieapp.view

import com.gb.movieapp.model.Movie

interface MovieCard {
    fun onMovieCardClicked(movie: Movie)
}