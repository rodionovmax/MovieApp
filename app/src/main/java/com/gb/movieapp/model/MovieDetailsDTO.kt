package com.gb.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsDTO(
    var id: Int,
    var original_title: String,
    var title: String,
    var poster_path: String,
    @SerializedName("genres") var genres: ArrayList<Genre>,
    var runtime: Int,
    var vote_average: Double,
    var budget: Long,
    var revenue: Long,
    var release_date: String,
    var overview: String
)

data class Genre(
    @SerializedName("name") var name: String,
)

