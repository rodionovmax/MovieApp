package com.gb.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.movieapp.model.Movies

class HomeViewModel : ViewModel() {

    // Get list of movies
    private var _moviesList = MutableLiveData<List<Movies>>()
    val moviesList: LiveData<List<Movies>>
        get() = _moviesList

}

