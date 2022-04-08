package com.gb.movieapp.view.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.gb.movieapp.BuildConfig
import com.gb.movieapp.databinding.FragmentDetailsBinding
import com.gb.movieapp.model.Movie
import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.model.getDefaultMovieDetails

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
    private val onLoadListener: MovieDetailsLoader.MovieDetailsLoaderListener =
        object : MovieDetailsLoader.MovieDetailsLoaderListener {
            override fun onLoaded(movieDetailsDTO: MovieDetailsDTO) {
                displayMovieDetails(movieDetailsDTO)
            }
            override fun onFailed(throwable: Throwable) {
                // TODO: Обработка ошибки
            }
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
        binding.detailsMainView.visibility = View.GONE
        binding.detailsLoadingLayout.visibility = View.VISIBLE
        val loader = MovieDetailsLoader(onLoadListener, movieDetailsBundle.id, BuildConfig.TMDB_API_KEY)
        loader.loadMovieDetails()
    }

    private fun displayMovieDetails(movieDetailsDTO: MovieDetailsDTO) {
        with(binding) {
            detailsMainView.visibility = View.VISIBLE
            detailsLoadingLayout.visibility = View.GONE
            originalTitleMovieDetails.text = movieDetailsDTO.original_title
            titleMovieDetails.text = movieDetailsDTO.title
            genreMovieDetails.text = movieDetailsDTO.genres.joinToString {
                it.name
            }
            durationMovieDetails.text = StringBuilder().run {
                this.append(movieDetailsDTO.runtime.toString()).append(" min.")
            }
            ratingMovieDetails.text = movieDetailsDTO.vote_average.toString()
            budgetValueMovieDetails.text = StringBuilder().run {
                this.append(movieDetailsDTO.budget.toString()).append(" \$")
            }
            revenueValueMovieDetails.text = StringBuilder().run {
                this.append(movieDetailsDTO.revenue.toString()).append(" \$")
            }
            releaseDateValueMovieDetails.text = movieDetailsDTO.release_date
            overviewMovieDetails.text = movieDetailsDTO.overview
        }
    }


}

