package com.gb.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var id: Int,
    var originalTitle: String,
    var title: String? = null,
    var posterUrl: String,
    var genres: List<String>? = null,
    var duration: Int? = null,
    var rating: Double,
    var budget: Long? = null,
    var revenue: Long? = null,
    var releaseDate: String,
    var releaseYear: Int? = null,
    var overview: String? = null
) : Parcelable


@Parcelize
data class Sections(
    val id: Int,
    val name: String,
) : Parcelable


fun getDefaultMovieDetails() = Movie(
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
    2021,
    "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man."
)

fun getMoviesList(): List<Movie> {
    return listOf(
        Movie(
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
            2021,
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man."
        ),
        Movie(
            414906,
            "The Batman",
            "The Batman",
            "@drawable/batman",
            listOf("Crime", "Mystery", "Thriller"),
            176,
            8.0,
            185000000,
            600000000,
            "2022-03-04",
            2022,
            "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler."
        ),
        Movie(
            833425,
            "No Exit",
            "No Exit",
            "@drawable/no_exit",
            listOf("Horror", "Thriller"),
            96,
            6.5,
            0,
            0,
            "2022-02-25",
            2022,
            "Stranded at a rest stop in the mountains during a blizzard, a recovering addict discovers a kidnapped child hidden in a car belonging to one of the people inside the building which sets her on a terrifying struggle to identify who among them is the kidnapper."
        ),
        Movie(
            568124,
            "Encanto",
            "Encanto",
            "@drawable/encanto",
            listOf("Animation", "Comedy", "Family", "Fantasy"),
            102,
            7.7,
            50000000,
            240000000,
            "2021-11-24",
            2021,
            "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope."
        ),
        Movie(
            928381,
            "Sans répit",
            "Restless",
            "@drawable/restless",
            listOf("Action", "Adventure", "Science Fiction"),
            95,
            5.8,
            0,
            0,
            "2022-02-25",
            2022,
            "After going to extremes to cover up an accident, a corrupt cop's life spirals out of control when he starts receiving threats from a mysterious witness."
        ),
        Movie(
            823625,
            "Blacklight",
            "Blacklight",
            "@drawable/blacklight",
            listOf("Action", "Thriller"),
            104,
            5.7,
            43000000,
            10000000,
            "2022-02-10",
            2022,
            "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power."
        ),
        Movie(
            753232,
            "The Commando",
            "The Commando",
            "@drawable/commando",
            listOf("Action", "Crime", "Thriller"),
            93,
            6.8,
            0,
            0,
            "2022-01-07",
            2022,
            "An elite DEA agent returns home after a failed mission when his family makes an unexpected discovery in their house – a stash of money worth \$3 million. They soon face the danger and threat of a newly released criminal and his crew, who will do whatever it takes to retrieve the money, including kidnap the agent’s daughters. Stakes are high and lives are at risk in this head-to-head battle as the agent stops at nothing to protect his family against the money-hungry criminals."
        ),
        Movie(
            476669,
            "The King's Man",
            "The King's Man",
            "@drawable/kingsman",
            listOf("Action", "Adventure", "Thriller", "War"),
            131,
            7.0,
            100000000,
            124005195,
            "2021-12-22",
            2021,
            "As a collection of history's worst tyrants and criminal masterminds gather to plot a war to wipe out millions, one man must race against time to stop them."
        ),
        Movie(
            512195,
            "Red Notice",
            "Red Notice",
            "@drawable/red_notice",
            listOf("Action", "Comedy", "Crime", "Thriller"),
            118,
            6.8,
            160000000,
            178143,
            "2021-11-04",
            2021,
            "An Interpol-issued Red Notice is a global alert to hunt and capture the world's most wanted. But when a daring heist brings together the FBI's top profiler and two rival criminals, there's no telling what will happen."
        ),
        Movie(
            800510,
            "Kimi",
            "Kimi",
            "@drawable/kimi",
            listOf("Thriller"),
            89,
            6.2,
            0,
            0,
            "2022-02-10",
            2022,
            "A tech worker with agoraphobia discovers recorded evidence of a violent crime but is met with resistance when she tries to report it. Seeking justice, she must do the thing she fears the most: she must leave her apartment."
        ),
    )
}


fun getFavoritesList(): List<Movie> {
    return mutableListOf(
        Movie(
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
            2021,
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man."
        ),
        Movie(
            414906,
            "The Batman",
            "The Batman",
            "@drawable/batman",
            listOf("Crime", "Mystery", "Thriller"),
            176,
            8.0,
            185000000,
            600000000,
            "2022-03-04",
            2022,
            "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler."
        ),
        Movie(
            476669,
            "The King's Man",
            "The King's Man",
            "@drawable/kingsman",
            listOf("Action", "Adventure", "Thriller", "War"),
            131,
            7.0,
            100000000,
            124005195,
            "2021-12-22",
            2021,
            "As a collection of history's worst tyrants and criminal masterminds gather to plot a war to wipe out millions, one man must race against time to stop them."
        ),
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
