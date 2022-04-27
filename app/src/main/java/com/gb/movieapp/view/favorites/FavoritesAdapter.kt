package com.gb.movieapp.view.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.gb.movieapp.R
import com.gb.movieapp.model.Favorites
import com.gb.movieapp.model.GenreMap
import com.gb.movieapp.model.Movie
import com.gb.movieapp.model.mapGenres
import com.gb.movieapp.utils.PICTURE_BASE_URL
import com.gb.movieapp.view.OnFavoritesCheckboxListener

class FavoritesAdapter(
    private var onMovieCardClickListener: FavoritesFragment.OnItemViewClickListener?,
    private var onFavoritesCheckboxListener: OnFavoritesCheckboxListener?,
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
            itemView.apply {
                findViewById<TextView>(R.id.title_favorites).text = favorites.originalTitle
                findViewById<TextView>(R.id.release_date_favorites).text = favorites.releaseDate
                findViewById<TextView>(R.id.genre_favorites).text = convertIdsToGenres(favorites.genreIds).toString().drop(1).dropLast(1)
                findViewById<TextView>(R.id.rating_favorites).text = favorites.rating.toString()
                findViewById<ImageView>(R.id.iv_poster).load(PICTURE_BASE_URL + favorites.posterUrl)

                // Handling click on a favorites card
                setOnClickListener {
                    onMovieCardClickListener?.onItemViewClick(favorites)
                }

                // Handling click on a favorites checkbox
                findViewById<CheckBox>(R.id.favorites_checkbox).run {
                    setOnClickListener {
                        onFavoritesCheckboxListener?.onItemChecked(this, favorites)
                    }
                }
            }
        }
    }

    // Convert genre_ids to genres
    private fun convertIdsToGenres(genreIdList: List<Int>?) : List<String> {
        val genres: MutableList<String> = mutableListOf()
        if (genreIdList != null) {
            for (genreId in genreIdList) {
                for (i in 0 .. mapGenres().size) {
                    if (genreId == mapGenres()[i].id) {
                        genres.add(mapGenres()[i].name)
                        break
                    }
                }
            }
        }
        return genres
    }
}