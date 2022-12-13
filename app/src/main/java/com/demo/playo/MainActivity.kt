package com.demo.playo

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.demo.playo.adapter.NewsAdapter
import com.demo.playo.databinding.ActivityMainBinding
import com.demo.playo.model.Article
import com.demo.playo.network.ApiInterface
import com.demo.playo.network.Responce
import com.demo.playo.repo.NewsRepo
import com.demo.playo.viewModel.NewsViewModel
import com.demo.playo.viewModelFactory.NewsViewModelFactory

class MainActivity : AppCompatActivity() {


    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mApi : ApiInterface
    private lateinit var mRepo : NewsRepo
    private lateinit var mViewModel : NewsViewModel
    private lateinit var mList : List<Article>
    private lateinit var mAdapter : NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mApi = ApiInterface.create()
        mRepo = NewsRepo(mApi , this)
        mViewModel = ViewModelProvider(this , NewsViewModelFactory(mRepo)).get(NewsViewModel::class.java)

        mViewModel.getNewsList("techcrunch" , "cc5781e43a7f49408ab5bf0cce40d45c")
        observeNewsList()
    }

    private fun observeNewsList() {
        mViewModel.newsList.observe(this){
            when (it) {
                is Responce.Loading -> {
                }
                is Responce.Success -> {
                    Log.e(TAG, "observeNewsList: "+it.data!!.totalResults, )

                    mList = it.data!!.articles
                    mAdapter = NewsAdapter(mList , this)
                    mBinding.recView.adapter = mAdapter

                }
                is Responce.NetworkError<*> -> {
                    //Toast.makeText(requireActivity(),"No Internet AvAilable.." ,Toast.LENGTH_SHORT).show()
                }
                is Responce.Error -> {
                    //Toast.makeText(requireActivity(),  ,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}