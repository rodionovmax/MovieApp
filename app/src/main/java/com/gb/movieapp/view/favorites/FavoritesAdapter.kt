package com.gb.movieapp.view.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.model.Favorites
import com.gb.movieapp.model.MovieDetails
import com.gb.movieapp.view.MovieCard

class FavoritesAdapter(
    private var onMovieCardClickListener: FavoritesFragment.OnItemViewClickListener?
    ) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private var favoritesList : List<Favorites> = listOf()

    fun setFavoritesList(data : List<Favorites>) {
        favoritesList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorites_item, parent, false)
//
//        val listener = view.context as OnFavoritesCardClickListener
//        return FavoritesViewHolder(view, listener)
        return FavoritesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorites_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
//        holder.tvFavoritesMovieTitle.text = favoritesList[position].originalTitle
//        holder.tvFavoritesReleaseDate.text = favoritesList[position].releaseDate
//        holder.tvFavoritesGenre.text = favoritesList[position].genres.joinToString()
//        holder.tvFavoritesRating.text = favoritesList[position].rating.toString()
        holder.bind(favoritesList[position])
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    inner class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val favoritesCard: CardView = itemView.findViewById(R.id.favorites_card)
//        val tvFavoritesMovieTitle : TextView = itemView.findViewById(R.id.textview_name_favorites)
//        val tvFavoritesReleaseDate : TextView = itemView.findViewById(R.id.textview_release_date_favorites)
//        val tvFavoritesGenre : TextView = itemView.findViewById(R.id.textview_genre_favorites)
//        val tvFavoritesRating : TextView = itemView.findViewById(R.id.textview_rating_favorites)
        fun bind(favorites: Favorites) {
            itemView.findViewById<TextView>(R.id.textview_name_favorites).text = favorites.originalTitle
            itemView.findViewById<TextView>(R.id.textview_release_date_favorites).text = favorites.releaseDate
            itemView.findViewById<TextView>(R.id.textview_genre_favorites).text = favorites.genres.joinToString()
            itemView.findViewById<TextView>(R.id.textview_rating_favorites).text = favorites.rating.toString()
            itemView.setOnClickListener {
                onMovieCardClickListener?.onItemViewClick(favorites)
            }
        }

//        init {
//            favoritesCard.setOnClickListener(this)
//        }
//
//        override fun onClick(v: View?) {
//            val position = adapterPosition
//            listener.onFavoritesCardClicked(position)
//        }

    }

    interface OnFavoritesCardClickListener {
        fun onFavoritesCardClicked(position: Int)
    }




}