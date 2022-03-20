package com.gb.movieapp.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.model.Favorites
import com.gb.movieapp.model.getFavorites
import com.gb.movieapp.model.getMovies
import com.gb.movieapp.view.MovieCard
import com.gb.movieapp.view.favorites.FavoritesFragment
import com.gb.movieapp.viewmodel.HomeViewModel
import com.gb.movieapp.viewmodel.HomeViewModelFactory

class HomeSectionAdapter : RecyclerView.Adapter<HomeSectionAdapter.HomeSectionViewHolder>() {


    // Creating items for sections adapter - temporary storing them here
    var sections: ArrayList<String> = arrayListOf(
        "Popular", "Now Playing", "Upcoming", "Top Rated"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSectionViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.section_home, parent, false)
        return HomeSectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeSectionViewHolder, position: Int) {
        val item = sections[position]
        holder.sectionTitle.text = item
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    class HomeSectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var listener = itemView.context as MovieCard

        var sectionTitle: TextView = itemView.findViewById(R.id.section_title)
//        private var adapter: HomeMovieAdapter = HomeMovieAdapter()
        private val adapter = HomeMovieAdapter(object : FavoritesFragment.OnItemViewClickListener {
            override fun onItemViewClick(favorites: Favorites) {
                listener.onMovieCardClicked(favorites)
            }
        })

        init {
            val homeMoviesRecycleView = itemView.findViewById<RecyclerView>(R.id.home_movie_list)
            homeMoviesRecycleView.adapter = adapter
            homeMoviesRecycleView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

//            homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//            val movies = homeViewModel.getMoviesFromLocal()
//            adapter.setMoviesList(movies)

            adapter.setMoviesList(getFavorites())
        }



    }



}


