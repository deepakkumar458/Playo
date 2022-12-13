package com.demo.playo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.playo.model.NewsModel
import com.demo.playo.network.Responce
import com.demo.playo.repo.NewsRepo
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NewsViewModel(val mRepo : NewsRepo) : ViewModel(){

    val newsList : MutableLiveData<Responce<NewsModel>>
    get() = mRepo.mNewsList

    fun getNewsList(sources:String , apiKey: String){
        viewModelScope.launch {
            mRepo.getNewsList(sources, apiKey)
        }
    }

}