package com.gb.movieapp.view.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.movieapp.R
import com.gb.movieapp.databinding.FragmentHomeBinding
import com.gb.movieapp.isShowMoviesWithRating8
import com.gb.movieapp.model.ChangeFavoritesDTO
import com.gb.movieapp.model.Movie
import com.gb.movieapp.model.Section
import com.gb.movieapp.model.getSections
import com.gb.movieapp.view.OnFavoritesCheckboxListener
import com.gb.movieapp.view.UpdateFavoritesListener
import com.gb.movieapp.viewmodel.AddToFavoritesViewModel
import com.gb.movieapp.viewmodel.AppState
import com.gb.movieapp.viewmodel.MovieListViewModel
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment(), OnFavoritesCheckboxListener, UpdateFavoritesListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var adapter: HomeSectionAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var mapData: MutableList<Pair<Section, List<Movie>>> = mutableListOf()

    private val addToFavoritesViewModel: AddToFavoritesViewModel by lazy {
        ViewModelProvider(this).get(
            AddToFavoritesViewModel::class.java
        )
    }

    private val movieListViewModel: MovieListViewModel by lazy {
        ViewModelProvider(this).get(
            MovieListViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeSectionsList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

//        for (sectionId in 0 until getSections().size) {
//        for (sectionId in 0 until 2) {
//            movieListViewModel.movieListLiveData.observe(viewLifecycleOwner) {
//                if (it != null) renderData(it, sectionId)
//            }
//            movieListViewModel.getMovieListFromRemoteSource(sectionId)
//        }

        /*
    *
    * Section(0, "Popular"),
        Section(1, "Now Playing"),
        Section(2, "Upcoming"),
        Section(3, "Top Rated"),*/

        movieListViewModel.getPopular().observe(viewLifecycleOwner) {
            if (it != null) renderData(it, 0)
        }

        movieListViewModel.getNowPlaying().observe(viewLifecycleOwner) {
            if (it != null) renderData(it, 1)
        }

        movieListViewModel.getUpcoming().observe(viewLifecycleOwner) {
            if (it != null) renderData(it, 2)
        }

        movieListViewModel.getTopRated().observe(viewLifecycleOwner) {
            if (it != null) renderData(it, 3)
        }
    }


    private fun renderData(appState: AppState, sectionId: Int) {
        when (appState) {
            is AppState.Success -> {
                val sectionName : String = getSections()[sectionId].name
                binding.homeFragmentLoadingLayout.visibility = View.GONE
                @Suppress("UNCHECKED_CAST")
                val list = appState.success as List<Movie>
                val filter = list.filter { movie ->
                    if (requireActivity().isShowMoviesWithRating8()) {
                        movie.rating >= 8
                    } else {
                        movie.rating > 0
                    }
                }
                mapData.add(Pair(Section(sectionId, sectionName), filter))
                if (mapData.size == 4) {
                    adapter = HomeSectionAdapter(mapData, this)
                    binding.homeSectionsList.adapter = adapter
                }
            }
            is AppState.Loading -> {
                binding.homeFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.homeFragmentLoadingLayout.visibility = View.GONE
                Toast.makeText(requireContext(), "Oops something went wrong with loading movies list...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun View.showSnackBar(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    override fun onItemChecked(p0: View, movie: Movie) {
        p0 as CheckBox
        val isChecked: Boolean = p0.isChecked
        when (p0.id) {
            R.id.checkbox_favorite_movie, R.id.favorites_checkbox -> if (isChecked) {
                updateFavorites(movie.id, true)
            } else {
                updateFavorites(movie.id, false)
            }
        }
    }

    override fun updateFavorites(movieId : Int, addedFlag : Boolean) {
        addToFavoritesViewModel.addToFavoritesLiveData.observe(viewLifecycleOwner) { renderDataToMarkAsFavorites(it, addedFlag) }
        addToFavoritesViewModel.markAsFavorite(movieId, addedFlag)
    }

    private fun renderDataToMarkAsFavorites(appState: AppState, addedFlag: Boolean) {
        when (appState) {
            is AppState.Success -> {
                val data = appState.success as ChangeFavoritesDTO
                if (data.success) {
                    if (addedFlag) {
                        Toast.makeText(requireContext(), "Added to Favorites", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Error ...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}