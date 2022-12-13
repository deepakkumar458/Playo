package com.demo.playo.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.demo.playo.MainActivity
import com.demo.playo.WebView
import com.demo.playo.databinding.NewsSingleRowLayoutBinding
import com.demo.playo.model.Article
import com.squareup.picasso.Picasso

class NewsAdapter(val mList : List<Article> , val mContext: Context):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(val mBinding : NewsSingleRowLayoutBinding):RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bsBinding = NewsSingleRowLayoutBinding.inflate(LayoutInflater.from(mContext) , parent , false)
        return ViewHolder(bsBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mList.get(position)

        holder.mBinding.titleTv.text = data.title
        holder.mBinding.authirTv.text = "By: "+data.author
        holder.mBinding.discriptionTv.text = data.description
        holder.mBinding.publishedTv.text = "At: "+data.publishedAt
        Picasso.get().load(data.urlToImage).into(holder.mBinding.imageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext , WebView::class.java)
            intent.putExtra("url" , data.url)
            (mContext as MainActivity).startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}