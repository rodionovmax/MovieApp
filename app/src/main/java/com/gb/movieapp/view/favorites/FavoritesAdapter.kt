package com.gb.movieapp.view.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.model.Movie

class FavoritesAdapter(
    private var onMovieCardClickListener: FavoritesFragment.OnItemViewClickListener?
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private var favoritesList: List<Movie> = listOf()

    fun setFavoritesList(data: List<Movie>) {
        favoritesList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorites_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(favoritesList[position])
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    inner class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favorites: Movie) {
            itemView.findViewById<TextView>(R.id.title_favorites).text = favorites.originalTitle
            itemView.findViewById<TextView>(R.id.release_date_favorites).text =
                favorites.releaseDate
            itemView.findViewById<TextView>(R.id.genre_favorites).text =
                favorites.genres.joinToString()
            itemView.findViewById<TextView>(R.id.rating_favorites).text =
                favorites.rating.toString()
            itemView.setOnClickListener {
                onMovieCardClickListener?.onItemViewClick(favorites)
            }
        }

    }

}