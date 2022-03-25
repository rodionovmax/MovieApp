package com.gb.movieapp.view.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.movieapp.R
import com.gb.movieapp.databinding.FragmentFavoritesBinding
import com.gb.movieapp.model.Movie
import com.gb.movieapp.view.MovieCardListener
import com.gb.movieapp.view.OnFavoritesCheckboxListener
import com.gb.movieapp.viewmodel.AppState
import com.gb.movieapp.viewmodel.FavoritesViewModel
import com.google.android.material.snackbar.Snackbar


class FavoritesFragment : Fragment() {

    private lateinit var listener: MovieCardListener
    private lateinit var favoritesListener: OnFavoritesCheckboxListener
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

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
        if (context is OnFavoritesCheckboxListener) {
            favoritesListener = context
        } else {
            throw RuntimeException(requireContext().toString())
        }
    }

    private lateinit var favoritesViewModel: FavoritesViewModel

    private val adapter = FavoritesAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(favorites: Movie) {
            listener.onMovieCardClicked(favorites)
        }
    },
        object : OnFavoritesCheckboxListener {
            override fun onItemChecked(p0: View, movie: Movie) {
                favoritesListener.onItemChecked(p0, movie)
            }
        })

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

        favoritesViewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        favoritesViewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        favoritesViewModel.getFavorites()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.favoritesFragmentLoadingLayout.visibility = View.GONE
                adapter.setFavoritesList(appState.success)
            }
            is AppState.Loading -> {
                binding.favoritesFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.favoritesFragmentLoadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.favoritesRecyclerView, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { favoritesViewModel.getFavorites() }
                    .show()
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(favorites: Movie)
    }


}