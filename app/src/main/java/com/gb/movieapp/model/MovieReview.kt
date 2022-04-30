package com.gb.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieReview(
    var id: Int,
    var title: String,
    var review: String? = null
) : Parcelable

fun getMovieReviewsLocal(): List<MovieReview> {
    return listOf(
        MovieReview(634649, "Spider-Man: No Way Home", "Nice movie. Recommended to watch"),
        MovieReview(414906, "The Batman", "Good movie but too long and sometimes boring"),
        MovieReview(
            476669,
            "The King's Man",
            "Адаптер для RecyclerView абсолютно стандартный. В методе bind отображаем " +
                    "элемент списка через объединение данных и вешаем listener для наглядности. " +
                    "Метод setData добавляет данные для отображения"
        ),
    )
}
