package com.gb.movieapp.view.rewievs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.model.MovieReview
import kotlinx.android.synthetic.main.fragment_reviews_recyclerview_item.view.*

class ReviewsAdapter : RecyclerView.Adapter<ReviewsAdapter.RecyclerItemViewHolder>() {

    private var data: List<MovieReview> = arrayListOf()

    fun setData(data: List<MovieReview>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_reviews_recyclerview_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class RecyclerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: MovieReview) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                with(itemView) {
                    recyclerViewMovieName.text = data.title
                    recyclerViewReview.text = data.review
                }
            }
        }
    }


}