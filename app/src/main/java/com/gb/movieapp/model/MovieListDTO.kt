package com.gb.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieListDTO(
    @SerializedName("results")
    var results: ArrayList<MovieResult>,
)


data class MovieResult(
    @SerializedName("id")
    var id: Int,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("vote_average")
    var voteAverage: Double,
)