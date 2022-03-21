package com.gb.movieapp.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.model.*
import com.gb.movieapp.view.MovieCard
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

        var listener = itemView.context as MovieCard

        private val adapter = HomeMovieAdapter(object : FavoritesFragment.OnItemViewClickListener {
            override fun onItemViewClick(favorites: Movie) {
                listener.onMovieCardClicked(favorites)
            }
        })

        fun bind(sections: Sections) {
            itemView.section_title.text = sections.name
            itemView.home_movie_list.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.home_movie_list.adapter = adapter
        }
    }


}


