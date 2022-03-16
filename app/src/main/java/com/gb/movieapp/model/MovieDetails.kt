package com.gb.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails(
    var id: Int,
    var originalTitle: String,
    var title: String,
    var posterUrl: String,
    var genres: List<String>,
    var duration: Int,
    var rating: Double,
    var budget: Long,
    var revenue: Long,
    var releaseDate: String,
    var overview: String
) : Parcelable

fun getDefaultMovieDetails() = MovieDetails(
    634649,
    "Spider-Man: No Way Home",
    "Spider-Man: No Way Home",
    "@drawable/mpd",
    listOf("Action", "Adventure", "Science Fiction"),
    148,
    8.3,
    200000000,
    1866488233,
    "2021-12-15",
    "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man."
)