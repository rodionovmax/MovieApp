package com.gb.movieapp.view.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private val favoritesList: ArrayList<String> = arrayListOf(
        "First favorite movie",
        "Second favorite movie",
        "Third favorite movie",
        "Fourth favorite movie",
        "Fifth favorite movie",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorites_item, parent, false)

        val listener = view.context as OnFavoritesCardClickListener
        return FavoritesViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val item = favoritesList[position]
        holder.tvFavoritesMovieTitle.text = item
        holder.tvFavoritesReleaseDate.text = "12/22/2021"
        holder.tvFavoritesGenre.text = "Action, Adventure, Thriller"
        holder.tvFavoritesRating.text = "7,1"
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    inner class FavoritesViewHolder(itemView: View, private val listener: OnFavoritesCardClickListener) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val favoritesCard: CardView = itemView.findViewById(R.id.favorites_card)
        val tvFavoritesMovieTitle : TextView = itemView.findViewById(R.id.textview_name_favorites)
        val tvFavoritesReleaseDate : TextView = itemView.findViewById(R.id.textview_release_date_favorites)
        val tvFavoritesGenre : TextView = itemView.findViewById(R.id.textview_genre_favorites)
        val tvFavoritesRating : TextView = itemView.findViewById(R.id.textview_rating_favorites)

        init {
            favoritesCard.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onFavoritesCardClicked(position)
        }

    }

    interface OnFavoritesCardClickListener {
        fun onFavoritesCardClicked(position: Int)
    }




}