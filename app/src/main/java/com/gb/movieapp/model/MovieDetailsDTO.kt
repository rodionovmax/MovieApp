package com.gb.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsDTO(
    @SerializedName("id")
    var id: Int,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("genres")
    var genres: ArrayList<Genre>,
    @SerializedName("runtime")
    var runtime: Int,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("budget")
    var budget: Long,
    @SerializedName("revenue")
    var revenue: Long,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("overview")
    var overview: String
)

data class Genre(
    @SerializedName("name") var name: String,
)

