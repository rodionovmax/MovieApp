package com.gb.movieapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorites(
    var id: Int,
    var original_title: String,
    var genres: List<String>,
    var poster_path: String,
    var release_date: String,
    var vote_average: Double,
) : Parcelable


data class FavoritesListDTO(
    @SerializedName("results") var results: ArrayList<Favorites>,
)
