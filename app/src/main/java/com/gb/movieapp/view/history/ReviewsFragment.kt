package com.gb.movieapp.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gb.movieapp.databinding.FragmentReviewsBinding
import com.gb.movieapp.model.MovieReview
import com.gb.movieapp.viewmodel.AppState
import com.gb.movieapp.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_reviews.*

class ReviewsFragment : Fragment() {
    private var _binding: FragmentReviewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReviewViewModel by lazy { ViewModelProvider(this).get(ReviewViewModel::class.java) }
    private val adapter: ReviewsAdapter by lazy { ReviewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reviewsFragmentRecyclerview.adapter = adapter
        viewModel.reviewLiveData.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getAllHistory()
    }
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.reviewsFragmentRecyclerview.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                adapter.setData(appState.success as List<MovieReview>)
            }
            is AppState.Loading -> {
                binding.reviewsFragmentRecyclerview.visibility = View.GONE
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.reviewsFragmentRecyclerview.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                Toast.makeText(requireContext(), "Oops looks like I can't retrieve history from the database ...", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        @JvmStatic
        fun newInstance() = ReviewsFragment()
    }
}