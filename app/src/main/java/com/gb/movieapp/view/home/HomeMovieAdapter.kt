package com.gb.movieapp.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.gb.movieapp.R
import com.gb.movieapp.model.Movie
import com.gb.movieapp.utils.PICTURE_BASE_URL
import com.gb.movieapp.view.OnFavoritesCheckboxListener
import com.gb.movieapp.view.favorites.FavoritesFragment

class HomeMovieAdapter(
    private var movieDataList: List<Movie>,
    private var onMovieCardClickListener: FavoritesFragment.OnItemViewClickListener?,
    private var onFavoritesCheckboxListener: OnFavoritesCheckboxListener?,
) : RecyclerView.Adapter<HomeMovieAdapter.HomeMovieViewHolder>() {

//    private var moviesDataList: List<Movie> = getMoviesList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return HomeMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) {
        holder.bind(movieDataList[position])
    }

    override fun getItemCount(): Int {
        return movieDataList.size
    }

    inner class HomeMovieViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.apply {
                findViewById<TextView>(R.id.movie_card_title).text = movie.originalTitle
                findViewById<TextView>(R.id.movie_card_year).text = movie.releaseYear
                findViewById<TextView>(R.id.movie_card_rating).text = movie.rating.toString()
                findViewById<ImageView>(R.id.iv_movie_poster).load(PICTURE_BASE_URL + movie.posterUrl)

                // Handling click on a movie card
                setOnClickListener {
                    onMovieCardClickListener?.onItemViewClick(movie)
                }

                // Handling click on a favorites checkbox
                findViewById<CheckBox>(R.id.checkbox_favorite_movie).run {
                    setOnClickListener {
                        onFavoritesCheckboxListener?.onItemChecked(this, movie)
                    }
                }
            }
        }
    }

}