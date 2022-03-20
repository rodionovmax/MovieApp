package com.gb.movieapp.view.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.databinding.FragmentFavoritesBinding
import com.gb.movieapp.databinding.FragmentHomeBinding
import com.gb.movieapp.model.Favorites
import com.gb.movieapp.model.Movies
import com.gb.movieapp.view.MovieCard
import com.gb.movieapp.view.favorites.FavoritesAdapter
import com.gb.movieapp.view.favorites.FavoritesFragment
import com.gb.movieapp.viewmodel.AppState
import com.gb.movieapp.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

//    companion object : ViewModelStoreOwner {
//        fun newInstance() = HomeFragment()
//    }

    companion object {
        fun newInstance() = HomeFragment()
//        override fun getViewModelStore(): ViewModelStore {
//            return HomeFragment.newInstance()
//        }
    }

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: HomeSectionAdapter
//    private lateinit var moviesAdapter: HomeMovieAdapter
//    private lateinit var listener : MovieCard
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

//    private val adapter = HomeSectionAdapter(object : FavoritesFragment.OnItemViewClickListener {
//        override fun onItemViewClick(favorites: Favorites) {
//            listener.onMovieCardClicked(favorites)
//        }
//    })

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is MovieCard) {
//            listener = context
//        } else {
//            throw RuntimeException(requireContext().toString() )
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return binding.getRoot()
        adapter = HomeSectionAdapter()
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeSectionsList.adapter = adapter
        binding.homeSectionsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        val homeContentRecyclerView: RecyclerView = view.findViewById(R.id.home_sections_list)
//        homeContentRecyclerView.adapter = adapter
//        homeContentRecyclerView.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getLiveData().observe(viewLifecycleOwner, {renderData(it)})
        homeViewModel.getMoviesFromLocal()
//
//        adapter.set(favorites)
    }

//    fun getMoviesList(): List<Movies> {
//        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//        return homeViewModel.getMoviesFromLocal()
//    }



    private fun renderData(appState: AppState) {
        when(appState) {
            is AppState.Success -> {
//                binding.favoritesFragmentLoadingLayout.visibility = View.GONE
//                adapter.setFavoritesList(appState.favoriteMovies)
                adapter.setMoviesList(appState.favoriteMovies)
            }
            is AppState.Loading -> {
//                binding.favoritesFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
//                binding.favoritesFragmentLoadingLayout.visibility = View.GONE
//                Snackbar
//                    .make(binding.mainFragmentFAB, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
//                    .setAction(getString(R.string.reload)) { favoritesViewModel.getFavorites() }
//                    .show()
            }
        }
    }


}