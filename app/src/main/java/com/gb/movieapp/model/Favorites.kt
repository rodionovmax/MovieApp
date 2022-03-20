package com.gb.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorites(
    val id: Int,
    val posterUrl: String,
    val originalTitle: String,
    val releaseDate: String,
    val genres: List<String>,
    val rating: Double
) : Parcelable


fun getFavoriteMovies(): List<Favorites> {
    return listOf(
        Favorites(634649, "@drawable/mpd", "Spider-Man: No Way Home","2021-12-15", listOf("Action", "Adventure", "Science Fiction"), 8.3),
        Favorites(414906, "@drawable/batman", "The Batman","2022-03-01",listOf("Crime", "Mystery", "Thriller"), 8.0),
        Favorites(833425, "@drawable/no_exit", "No Exit","2022-02-25",listOf("Horror", "Thriller"), 6.5),
        Favorites(568124, "@drawable/encanto", "Encanto","2021-11-24",listOf("Animation", "Comedy", "Family", "Fantasy"), 7.7),
        Favorites(928381, "@drawable/restless", "Sans répit","2022-02-25",listOf("Action", "Adventure", "Science Fiction"), 5.9),
        Favorites(823625, "@drawable/blacklight", "Blacklight","2022-02-10",listOf("Action", "Thriller"), 5.2),
        Favorites(753232, "@drawable/commando", "The Commando","2022-01-07",listOf("Action", "Crime", "Thriller"), 6.6),
        Favorites(476669, "@drawable/kingsman", "The King's Man","2021-12-22",listOf("Action", "Adventure", "Thriller", "War"), 7.0),
        Favorites(512195, "@drawable/red_notice", "Red Notice","2021-11-04",listOf("Action", "Comedy", "Crime", "Thriller"), 6.8),
        Favorites(800510, "@drawable/kimi", "Kimi","2022-02-10",listOf("Thriller"), 6.3),
    )
}