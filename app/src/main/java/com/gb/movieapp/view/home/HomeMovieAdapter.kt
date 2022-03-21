package com.gb.movieapp.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.model.Movie
import com.gb.movieapp.model.getMoviesList
import com.gb.movieapp.view.favorites.FavoritesFragment

class HomeMovieAdapter(
    private var onMovieCardClickListener: FavoritesFragment.OnItemViewClickListener?
) : RecyclerView.Adapter<HomeMovieAdapter.HomeMovieViewHolder>() {

    private var moviesDataList: List<Movie> = getMoviesList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return HomeMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) {
        holder.bind(moviesDataList[position])
    }

    override fun getItemCount(): Int {
        return moviesDataList.size
    }

    inner class HomeMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.findViewById<TextView>(R.id.movie_card_title).text = movie.originalTitle
            itemView.findViewById<TextView>(R.id.movie_card_year).text =
                movie.releaseYear.toString()
            itemView.findViewById<TextView>(R.id.movie_card_rating).text = movie.rating.toString()
            itemView.setOnClickListener {
                onMovieCardClickListener?.onItemViewClick(movie)
            }
        }
    }


}