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


@Parcelize
data class Sections(
    val id: Int,
    val name: String,
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

fun getFavorites(): List<Favorites> {
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


fun getSections(): List<Sections> {
    return listOf(
        Sections(1, "Popular"),
        Sections(2, "Now Playing"),
        Sections(3, "Upcoming"),
        Sections(4, "Top Rated"),
    )
}
