package com.gb.movieapp.view.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.gb.movieapp.databinding.FragmentDetailsBinding
import com.gb.movieapp.model.Movie
import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.model.MovieReview
import com.gb.movieapp.model.getDefaultMovieDetails
import com.gb.movieapp.utils.PICTURE_BASE_URL
import com.gb.movieapp.viewmodel.AppState
import com.gb.movieapp.viewmodel.MovieDetailsViewModel
import com.gb.movieapp.viewmodel.ReviewViewModel

class DetailsFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "movie"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieDetailsBundle: Movie

    private val detailsViewModel: MovieDetailsViewModel by lazy {
        ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
    }

    // TODO: create base View Model and move these methods there
    private val reviewsViewModel: ReviewViewModel by lazy {
        ViewModelProvider(this).get(ReviewViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: getDefaultMovieDetails()

        detailsViewModel.detailsLiveData.observe(viewLifecycleOwner) { renderData(it) }
        detailsViewModel.getMovieDetailsFromRemoteSource(movieDetailsBundle.id)

        // Click Write Review button to make review edit text and send button visible
        with(binding) {
            writeReviewBtn.setOnClickListener {
                etReview.visibility = View.VISIBLE
                sendBtn.visibility = View.VISIBLE
            }

            // write review to DB
            sendBtn.setOnClickListener {
                reviewsViewModel.addReview(
                    MovieReview(
                        movieId = movieDetailsBundle.id,
                        title = movieDetailsBundle.originalTitle,
                        review = etReview.text.toString()
                    )
                )
            }
        }


    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.detailsMainView.visibility = View.VISIBLE
                binding.detailsLoadingLayout.visibility = View.GONE
                displayMovieDetails(appState.success as MovieDetailsDTO)
            }
            is AppState.Loading -> {
                binding.detailsMainView.visibility = View.GONE
                binding.detailsLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.detailsMainView.visibility = View.VISIBLE
                binding.detailsLoadingLayout.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    "Oops something went wrong with loading movie details...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun displayMovieDetails(movieDetailsDTO: MovieDetailsDTO) {
        with(binding) {
            detailsMainView.visibility = View.VISIBLE
            detailsLoadingLayout.visibility = View.GONE
            originalTitleMovieDetails.text = movieDetailsDTO.originalTitle
            titleMovieDetails.text = movieDetailsDTO.title
            ivPoster.load(PICTURE_BASE_URL + movieDetailsDTO.posterPath)
            genreMovieDetails.text = movieDetailsDTO.genres.joinToString {
                it.name
            }
            durationMovieDetails.text = StringBuilder().run {
                this.append(movieDetailsDTO.runtime.toString()).append(" min.")
            }
            ratingMovieDetails.text = movieDetailsDTO.voteAverage.toString()
            budgetValueMovieDetails.text = StringBuilder().run {
                this.append(movieDetailsDTO.budget.toString()).append(" \$")
            }
            revenueValueMovieDetails.text = StringBuilder().run {
                this.append(movieDetailsDTO.revenue.toString()).append(" \$")
            }
            releaseDateValueMovieDetails.text = movieDetailsDTO.releaseDate
            overviewMovieDetails.text = movieDetailsDTO.overview
        }
    }


}

