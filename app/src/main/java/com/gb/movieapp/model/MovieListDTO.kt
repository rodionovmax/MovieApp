package com.gb.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieListDTO(
    @SerializedName("results") var results: ArrayList<MovieResult>,
)


data class MovieResult(
    var id: Int,
    var original_title: String,
    var poster_path: String,
    var release_date: String,
    var vote_average: Double,
)