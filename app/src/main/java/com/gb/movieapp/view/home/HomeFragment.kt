package com.gb.movieapp.view.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.movieapp.R
import com.gb.movieapp.databinding.FragmentHomeBinding
import com.gb.movieapp.model.Movie
import com.gb.movieapp.model.Section
import com.gb.movieapp.model.getSections
import com.gb.movieapp.viewmodel.AppState
import com.gb.movieapp.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var adapter: HomeSectionAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var mapData: MutableList<Pair<Section, List<Movie>>> = mutableListOf()

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(
            HomeViewModel::class.java
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

        for (sectionId in 0 until getSections().size) {
            homeViewModel.getMoviesFromServer(sectionId).observe(viewLifecycleOwner) {
                if (it != null) renderData(it, sectionId)
            }
        }
    }


    private fun renderData(appState: AppState, sectionId: Int) {
        when (appState) {
            is AppState.Success -> {
                val sectionName : String = getSections()[sectionId].name
                binding.homeFragmentLoadingLayout.visibility = View.GONE
                mapData.add(Pair(Section(sectionId, sectionName), appState.success))
                adapter = HomeSectionAdapter(mapData)
                binding.homeSectionsList.adapter = adapter
            }
            is AppState.Loading -> {
                binding.homeFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.homeFragmentLoadingLayout.visibility = View.GONE
                Snackbar
                    .make(
                        binding.homeSectionsList,
                        getString(R.string.error),
                        Snackbar.LENGTH_INDEFINITE
                    )
                    .setAction(getString(R.string.reload)) { homeViewModel.getMoviesFromLocal() }
                    .show()
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


}