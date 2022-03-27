package com.gb.movieapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gb.movieapp.databinding.FragmentDetailsBinding
import com.gb.movieapp.model.Movie

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Movie>(BUNDLE_EXTRA)?.let { movie ->
            binding.originalTitleMovieDetails.text = movie.originalTitle
            binding.titleMovieDetails.text = movie.title
            binding.genreMovieDetails.text = movie.genres.joinToString()
            binding.durationMovieDetails.text = StringBuilder().run {
                this.append(movie.duration.toString()).append(" min.")
            }
            binding.ratingMovieDetails.text = movie.rating.toString()
            binding.budgetValueMovieDetails.text = StringBuilder().run {
                this.append(movie.budget.toString()).append(" \$")
            }
            binding.revenueValueMovieDetails.text = StringBuilder().run {
                this.append(movie.revenue.toString()).append(" \$")
            }
            binding.releaseDateValueMovieDetails.text = movie.releaseDate
            binding.overviewMovieDetails.text = movie.overview
        }
    }


}

