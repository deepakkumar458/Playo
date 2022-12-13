package com.demo.playo.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.playo.repo.NewsRepo
import com.demo.playo.viewModel.NewsViewModel

class NewsViewModelFactory (private val mRepo: NewsRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(mRepo) as T
    }

}