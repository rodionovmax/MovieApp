package com.gb.movieapp.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.model.*
import com.gb.movieapp.view.MovieCardListener
import com.gb.movieapp.view.OnFavoritesCheckboxListener
import com.gb.movieapp.view.favorites.FavoritesFragment
import kotlinx.android.synthetic.main.section_home.view.*

class HomeSectionAdapter : RecyclerView.Adapter<HomeSectionAdapter.HomeSectionViewHolder>() {

    private var sections: List<Sections> = getSections()
    private var moviesDataList: List<Movie> = getMoviesList()

    fun setMoviesList(data: List<Movie>) {
        moviesDataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSectionViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.section_home, parent, false)
        return HomeSectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeSectionViewHolder, position: Int) {
        holder.bind(sections[position])
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    inner class HomeSectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var listener = itemView.context as MovieCardListener
        var favoritesListener = itemView.context as OnFavoritesCheckboxListener

        private val adapter = HomeMovieAdapter(
            object : FavoritesFragment.OnItemViewClickListener {
                override fun onItemViewClick(favorites: Movie) {
                    listener.onMovieCardClicked(favorites)
                }
            },
            object : OnFavoritesCheckboxListener {
                override fun onItemChecked(p0: View, movie: Movie) {
                    favoritesListener.onItemChecked(p0, movie)
                }
            }
        )

        fun bind(sections: Sections) {
            itemView.apply {
                section_title.text = sections.name
                home_movie_list.layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                home_movie_list.adapter = adapter
            }
        }
    }


}


