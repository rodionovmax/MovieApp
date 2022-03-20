package com.gb.movieapp.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.gb.movieapp.R
import com.gb.movieapp.databinding.FragmentDetailsBinding
import com.gb.movieapp.model.Favorites
import com.gb.movieapp.view.MainActivity
import com.gb.movieapp.view.home.HomeFragment

class DetailsFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "movie"
//        fun newInstance() = DetailsFragment()
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
//        activity?.supportFragmentManager?.addOnBackStackChangedListener(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_details, container, false)
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable<Favorites>(BUNDLE_EXTRA)
        if (movie != null) {
//            val city = weather.city
            binding.localizedMovieNameDetails.text = movie.originalTitle
            binding.originalMovieNameDetails.text = movie.originalTitle
            binding.genreDetails.text = movie.genres.joinToString()
            binding.durationDetails.text = movie.posterUrl
            binding.ratingDetails.text = movie.rating.toString()
            binding.budgetValueDetails.text = movie.id.toString()
            binding.revenueValueDetails.text = (movie.id + 1234567).toString()
            binding.releaseDateValueDetails.text = movie.releaseDate
            binding.movieOverviewDetails.text = movie.originalTitle
//            binding.cityName.text = city.city
//            binding.cityCoordinates.text = String.format(
//                getString(R.string.city_coordinates),
//                city.lat.toString(),
//                city.lon.toString()
//            )
//            binding.temperatureValue.text = weather.temperature.toString()
//            binding.feelsLikeValue.text = weather.feelsLike.toString()
        }
    }

//    override fun onBackStackChanged() {
//        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
//    }


}

