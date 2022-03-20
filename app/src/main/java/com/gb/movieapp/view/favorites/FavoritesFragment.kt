package com.gb.movieapp.view.favorites

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.movieapp.databinding.FragmentFavoritesBinding
import com.gb.movieapp.model.Favorites
import com.gb.movieapp.view.MovieCard
import com.gb.movieapp.viewmodel.AppState
import com.gb.movieapp.viewmodel.FavoritesViewModel


class FavoritesFragment : Fragment()  {

//    private lateinit var listener : FavoritesAdapter.OnFavoritesCardClickListener
    private lateinit var listener : MovieCard
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieCard) {
            listener = context
        } else {
            throw RuntimeException(requireContext().toString() )
        }
    }

    private lateinit var favoritesViewModelProvider: ViewModelProvider
    private lateinit var favoritesViewModel : FavoritesViewModel
//    private lateinit var adapter: FavoritesAdapter
//    private val adapter = FavoritesAdapter(object : OnItemViewClickListener {
    private val adapter = FavoritesAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(favorites: Favorites) {
//            val manager = activity?.supportFragmentManager
//            if (manager != null) {
//                val bundle = Bundle()
//                bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, favorites)
//                manager.beginTransaction()
////                    .add(R.id.container, DetailsFragment.newInstance(bundle))
//                    .add(R.id.main_fragment_holder, DetailsFragment.newInstance(bundle))
//                    .addToBackStack("")
//                    .commitAllowingStateLoss()
//            }
            listener.onMovieCardClicked(favorites)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.getRoot()
//        adapter = FavoritesAdapter()
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val favoritesRecyclerView: RecyclerView = view.findViewById(R.id.favorites_list)
        binding.favoritesRecyclerView.adapter = adapter
        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        favoritesViewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        favoritesViewModel.getLiveData().observe(viewLifecycleOwner, {renderData(it)})
        favoritesViewModel.getFavorites()
//        val favorites = favoritesViewModel.getFavorites()
//        adapter.setFavoritesList(favorites)
    }

    private fun renderData(appState: AppState) {
        when(appState) {
            is AppState.Success -> {
                binding.favoritesFragmentLoadingLayout.visibility = View.GONE
                adapter.setFavoritesList(appState.favoriteMovies)
            }
            is AppState.Loading -> {
                binding.favoritesFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.favoritesFragmentLoadingLayout.visibility = View.GONE
//                Snackbar
//                    .make(binding.mainFragmentFAB, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
//                    .setAction(getString(R.string.reload)) { favoritesViewModel.getFavorites() }
//                    .show()
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(favorites: Favorites)
    }


}