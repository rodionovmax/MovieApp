package com.gb.movieapp.view.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.movieapp.BuildConfig
import com.gb.movieapp.R
import com.gb.movieapp.databinding.FragmentFavoritesBinding
import com.gb.movieapp.model.Movie
import com.gb.movieapp.view.MovieCardListener
import com.gb.movieapp.view.OnFavoritesCheckboxListener
import com.gb.movieapp.view.UpdateFavoritesListener
import com.gb.movieapp.viewmodel.AppState
import com.gb.movieapp.viewmodel.MyViewModel
import com.google.android.material.snackbar.Snackbar


class FavoritesFragment : Fragment(), OnFavoritesCheckboxListener, UpdateFavoritesListener {

    private lateinit var listener: MovieCardListener
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val myViewModel: MyViewModel by lazy {
        ViewModelProvider(this).get(
            MyViewModel::class.java
        )
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieCardListener) {
            listener = context
        } else {
            throw RuntimeException(requireContext().toString())
        }
    }

    private val adapter = FavoritesAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(favorites: Movie) {
            listener.onMovieCardClicked(favorites)
        }
    },
        this
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoritesRecyclerView.adapter = adapter
        binding.favoritesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        getFavoritesListFromViewModel()
    }

    private fun getFavoritesListFromViewModel() {
        myViewModel.getFavoritesFromServer().observe(viewLifecycleOwner) {
            if (it != null) renderData(it)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.favoritesFragmentLoadingLayout.visibility = View.GONE
                @Suppress("UNCHECKED_CAST")
                adapter.setFavoritesList(appState.success as List<Movie>)
            }
            is AppState.Loading -> {
                binding.favoritesFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.favoritesFragmentLoadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.favoritesRecyclerView, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { myViewModel.getFavoritesFromLocal() }
                    .show()
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(favorites: Movie)
    }

    // Remove from favorites
    override fun onItemChecked(p0: View, movie: Movie) {
        p0 as CheckBox
        val isChecked: Boolean = p0.isChecked
        when (p0.id) {
            R.id.favorites_checkbox -> if (isChecked) {
                updateFavorites(movie.id, false)
            } else {
                updateFavorites(movie.id, true)
            }
        }
    }

    override fun updateFavorites(movieId: Int, addedFlag: Boolean) {
        myViewModel.markAsFavorite(movieId, addedFlag, BuildConfig.TMDB_SESSION_ID).observe(viewLifecycleOwner) {
            if (it != null) renderDataToMarkAsFavorites(it)
        }
    }

    private fun renderDataToMarkAsFavorites(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val data = appState.success as Boolean
                binding.favoritesFragmentLoadingLayout.visibility = View.GONE
                if (data) {
                    Toast.makeText(requireContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show()
                    // Update favorites list
                    getFavoritesListFromViewModel()
                }
            }
            is AppState.Loading -> {
                binding.favoritesFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.favoritesFragmentLoadingLayout.visibility = View.GONE
                Toast.makeText(requireContext(), "Error ...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}