package com.gb.movieapp.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R

class HomeContentAdapter : RecyclerView.Adapter<HomeContentAdapter.HomeContentViewHolder>() {

    // Creating items for sections adapter - temporary storing them here
    var sections: ArrayList<String> = arrayListOf(
        "Popular", "Now Playing", "Upcoming", "Top Rated"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeContentViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.content_home, parent, false)
        return HomeContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeContentViewHolder, position: Int) {
        val item = sections[position]
        holder.sectionTitle.text = item
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    class HomeContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sectionTitle: TextView = itemView.findViewById(R.id.section_title)
        private var adapter: HomeMovieAdapter = HomeMovieAdapter()

        init {
            val homeMoviesRecycleView = itemView.findViewById<RecyclerView>(R.id.home_movie_list)
            homeMoviesRecycleView.adapter = adapter
            homeMoviesRecycleView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

}


