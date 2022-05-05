package com.example.rxjavaretrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rxjavaretrofit.repository.CovidRepository


class CovidViewModelFactory(private val repository: CovidRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CovidViewModel::class.java)) {
            return CovidViewModel(repository) as T
        }
        throw IllegalArgumentException("Not found ViewModel class.")    }
}
