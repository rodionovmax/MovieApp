package com.gb.movieapp.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.model.*
import com.gb.movieapp.view.MovieCard
import com.gb.movieapp.view.favorites.FavoritesFragment
import com.gb.movieapp.viewmodel.HomeViewModel
import com.gb.movieapp.viewmodel.HomeViewModelFactory
import kotlinx.android.synthetic.main.section_home.view.*

class HomeSectionAdapter : RecyclerView.Adapter<HomeSectionAdapter.HomeSectionViewHolder>() {

    private var sections : List<Sections> = getSections()
    private var moviesDataList : List<Favorites> = getFavorites()

    fun setMoviesList(data : List<Favorites>) {
        moviesDataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSectionViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.section_home, parent, false)
        return HomeSectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeSectionViewHolder, position: Int) {
//        val item = sections[position].name
//        holder.sectionTitle.text = item
        holder.bind(sections[position])
    }

    override fun getItemCount(): Int {
        return sections.size
    }

//    var onItemClick: ((Favorites) -> Unit)? = null

    inner class HomeSectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        init {
//            itemView.setOnClickListener {
//                onItemClick?.invoke(moviesDataList[adapterPosition])
//
//            }
//        }

        var listener = itemView.context as MovieCard

        var sectionTitle: TextView = itemView.findViewById(R.id.section_title)
        private val adapter = HomeMovieAdapter(object : FavoritesFragment.OnItemViewClickListener {
            override fun onItemViewClick(favorites: Favorites) {
                listener.onMovieCardClicked(favorites)
            }
        })

        fun bind(sections: Sections) {
            itemView.section_title.text = sections.name
//            val homeMovieAdapter = HomeMovieAdapter(result.members)
            itemView.home_movie_list.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.home_movie_list.adapter = adapter
//            adapter.setMoviesList(getFavorites())  // Movies don't display without this line
        }

//        init {
//            val homeMoviesRecycleView = itemView.findViewById<RecyclerView>(R.id.home_movie_list)
//            homeMoviesRecycleView.adapter = adapter
//            homeMoviesRecycleView.layoutManager =
//                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
//
////            homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
////            val movies = homeViewModel.getMoviesFromLocal()
////            adapter.setMoviesList(movies)
//
////            adapter.setMoviesList(getFavorites())
//        }




    }



}


