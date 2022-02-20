package com.gb.movieapp.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R

class HomeMovieAdapter : RecyclerView.Adapter<HomeMovieAdapter.HomeMovieViewHolder>() {

    // Creating items for movies adapter - temporary storing them here
    private val moviesList : ArrayList<String> = arrayListOf(
        "First Movie",
        "Second Movie",
        "Third Movie",
        "Fourth Movie",
        "Fifth Movie",
        "Sixth Movie",
        "Seventh Movie",
        "Eight Movie",
        "Ninth Movie",
        "Tenth Movie",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return HomeMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) {
        val item = moviesList[position]
        holder.tvMovieName.text = item
        holder.tvMovieYear.text = "2021"
        holder.tvMovieRating.text = "8.4"
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    class HomeMovieViewHolder(
            itemView: View,
            val tvMovieName: TextView = itemView.findViewById(R.id.movie_card_title),
            val tvMovieYear: TextView = itemView.findViewById(R.id.movie_card_year),
            val tvMovieRating: TextView = itemView.findViewById(R.id.movie_card_rating)
        ) : RecyclerView.ViewHolder(itemView)

    init {
        // in this holder I initialized elements in primary constructor
    }
}