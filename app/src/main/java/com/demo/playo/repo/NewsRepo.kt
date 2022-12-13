package com.demo.playo.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.demo.playo.model.NewsModel
import com.demo.playo.network.ApiInterface
import com.demo.playo.network.Responce

class NewsRepo(val mApi : ApiInterface , val mContext: Context) {

    val mNewsList = MutableLiveData<Responce<NewsModel>>()

    suspend fun getNewsList(sources : String , apiKey : String){
        val result = mApi.getNewsList(sources , apiKey)
        if (result.body()!!.totalResults!= null) {
            mNewsList.postValue(Responce.Success(result.body()))
        } else {
            mNewsList.postValue(
                Responce.Error(
                    result.body()!!.status
                )
            )
        }

    }


}