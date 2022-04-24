package com.gb.movieapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorites(
    var id: Int,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("genres")
    var genres: List<String>,
    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("vote_average")
    var voteAverage: Double,
) : Parcelable


data class FavoritesListDTO(
    @SerializedName("results") var results: ArrayList<Favorites>,
)

data class ChangeFavoritesDTO(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("status_code")
    var statusCode: Int,
    @SerializedName("status_message")
    var statusMessage: String,
)