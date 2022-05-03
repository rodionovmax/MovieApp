package com.gb.movieapp

import android.app.Activity
import android.content.Context

private const val RATING_8_AND_HIGHER = "RATING_8"

fun Activity.setValueShowMovieWithRating8(isRatingEight: Boolean) {
    with(getPreferences(Context.MODE_PRIVATE).edit()) {
        putBoolean(RATING_8_AND_HIGHER, isRatingEight)
        apply()
    }
}

fun Activity.isShowMoviesWithRating8() : Boolean {
    return getPreferences(Context.MODE_PRIVATE).getBoolean(RATING_8_AND_HIGHER, false)
}
