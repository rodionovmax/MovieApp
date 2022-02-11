package com.gb.movieapp.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(
    private val liveDataToObserve: MutableLiveData<String> = MutableLiveData()
) :
    ViewModel() {

}

