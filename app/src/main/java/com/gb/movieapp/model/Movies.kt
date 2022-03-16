package com.gb.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    val id: Int,
    val posterUrl: String,
    val originalTitle: String,
    val year: Int,
    val rating: Double
) : Parcelable


fun getMovies(): List<Movies> {
    return listOf(
        Movies(634649, "@drawable/mpd", "Spider-Man: No Way Home",2021,8.3),
        Movies(414906, "@drawable/batman", "The Batman",2022,8.0),
        Movies(833425, "@drawable/no_exit", "No Exit",2022,6.5),
        Movies(568124, "@drawable/encanto", "Encanto",2021,7.7),
        Movies(928381, "@drawable/restless", "Sans répit",2022,5.9),
        Movies(823625, "@drawable/blacklight", "Blacklight",2022,5.2),
        Movies(753232, "@drawable/commando", "The Commando",2022,6.6),
        Movies(476669, "@drawable/kingsman", "The King's Man",2021,7.0),
        Movies(512195, "@drawable/red_notice", "Red Notice",2021,6.8),
        Movies(800510, "@drawable/kimi", "Kimi",2022,6.3),
    )
}
