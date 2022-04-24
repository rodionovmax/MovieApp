package com.gb.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModel::class.java)) {
            return com.gb.movieapp.viewmodel.MyViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}