package com.gb.movieapp.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.view.MainActivity
import com.gb.movieapp.view.details.DetailsFragment

class HomeMovieAdapter : RecyclerView.Adapter<HomeMovieAdapter.HomeMovieViewHolder>() {

    // Creating items for movies adapter - temporary storing them here
    private val moviesList: ArrayList<String> = arrayListOf(
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

    inner class HomeMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieCard: CardView = itemView.findViewById(R.id.movie_card)
        val tvMovieName: TextView = itemView.findViewById(R.id.movie_card_title)
        val tvMovieYear: TextView = itemView.findViewById(R.id.movie_card_year)
        val tvMovieRating: TextView = itemView.findViewById(R.id.movie_card_rating)

        init {
            movieCard.setOnClickListener {
                val activity = itemView.context as AppCompatActivity
                activity
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_fragment_holder, DetailsFragment.newInstance())
                    .addToBackStack("")
                    .commit()
            }
        }

    }



}