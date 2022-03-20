package com.gb.movieapp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.model.Favorites
import com.gb.movieapp.model.Movies
import com.gb.movieapp.view.MainActivity
import com.gb.movieapp.view.details.DetailsFragment
import com.gb.movieapp.view.favorites.FavoritesFragment

class HomeMovieAdapter(
    private var onMovieCardClickListener: FavoritesFragment.OnItemViewClickListener?
) : RecyclerView.Adapter<HomeMovieAdapter.HomeMovieViewHolder>() {

    private var moviesDataList : List<Favorites> = listOf()

    fun setMoviesList(data : List<Favorites>) {
        moviesDataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return HomeMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) {
//        val item = moviesList[position]
//        holder.tvMovieName.text = moviesDataList[position].originalTitle
//        holder.tvMovieYear.text = moviesDataList[position].year.toString()
//        holder.tvMovieRating.text = moviesDataList[position].rating.toString()
        holder.bind(moviesDataList[position])
    }

    override fun getItemCount(): Int {
        return moviesDataList.size
    }

    inner class HomeMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favorites: Favorites) {
            itemView.findViewById<TextView>(R.id.movie_card_title).text = favorites.originalTitle
            itemView.findViewById<TextView>(R.id.movie_card_year).text = favorites.releaseDate
            itemView.findViewById<TextView>(R.id.movie_card_rating).text = favorites.rating.toString()
//            itemView.findViewById<TextView>(R.id.textview_rating_favorites).text = favorites.rating.toString()
            itemView.setOnClickListener {
                onMovieCardClickListener?.onItemViewClick(favorites)
            }
        }
//        private val movieCard: CardView = itemView.findViewById(R.id.movie_card)
//        val tvMovieName: TextView = itemView.findViewById(R.id.movie_card_title)
//        val tvMovieYear: TextView = itemView.findViewById(R.id.movie_card_year)
//        val tvMovieRating: TextView = itemView.findViewById(R.id.movie_card_rating)

//        init {
//            movieCard.setOnClickListener {
//                val activity = itemView.context as AppCompatActivity
//                activity
//                    .supportFragmentManager
//                    .beginTransaction()
////                    .replace(R.id.main_fragment_holder, DetailsFragment.newInstance())
//                    .addToBackStack("")
//                    .commit()
//
////                val manager = activity?.supportFragmentManager
////                if (manager != null) {
////                    val bundle = Bundle()
////                    bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, movie)
////                    manager.beginTransaction()
////                        .add(R.id.container, DetailsFragment.newInstance(bundle))
////                        .addToBackStack("")
////                        .commitAllowingStateLoss()
////                }
//            }
//        }

    }



}