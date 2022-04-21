package com.gb.movieapp.model

data class GenreMap(
    val id: Int,
    val name: String
)


fun mapGenres(): List<GenreMap> {
    return listOf(
        GenreMap(28, "Action"),
        GenreMap(12, "Adventure"),
        GenreMap(16, "Animation"),
        GenreMap(35, "Comedy"),
        GenreMap(80, "Crime"),
        GenreMap(99, "Documentary"),
        GenreMap(18, "Drama"),
        GenreMap(10751, "Family"),
        GenreMap(14, "Fantasy"),
        GenreMap(36, "History"),
        GenreMap(27, "Horror"),
        GenreMap(10402, "Music"),
        GenreMap(9648, "Mystery"),
        GenreMap(10749, "Romance"),
        GenreMap(878, "Science Fiction"),
        GenreMap(10770, "Science Fiction"),
        GenreMap(878, "TV Movie"),
        GenreMap(53, "Thriller"),
        GenreMap(10752, "War"),
        GenreMap(37, "Western"),
    )
}