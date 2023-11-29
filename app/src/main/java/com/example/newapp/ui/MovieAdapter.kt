package com.example.newapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newapp.R
import com.example.newapp.data.dataSource.modul.Movies
import com.example.newapp.databinding.ArticleItemLayoutBinding

class MovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var list = emptyList<Movies?>()

    private var itemClickListener: ((Movies) -> Unit)? = null
    fun setOnClickListener(f: (Movies) -> Unit){
        itemClickListener = f
    }

    fun submitList(data: List<Movies?>?){
        list = data ?: emptyList()
        notifyDataSetChanged()
    }

    private inner class MoviesViewHolder(private val binding: ArticleItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("SetTextI18n")
            fun bind(item: Movies) {
                binding.apply {

                    if (item.posterPath != null) {
                        Glide.with(binding.root).load("https://image.tmdb.org/t/p/original" + item.posterPath).centerCrop()
                            .placeholder(R.drawable.bankrupt).into(imageView)
                        progressBar.visibility = View.GONE
                    } /*else {
                        imageView.setImageResource(R.drawable.logo_transformed)
                        progressBar.visibility = View.GONE
                    }*/

                    releaseDate.text = item.releaseDate?.substring(0, 10) ?: "Unknown"
                    title.text = item.title
                    overview.text = item.overview
                    originalLanguage.text = item.originalLanguage

                    root.setOnClickListener { itemClickListener?.invoke(item) }
                }
            }
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoviesViewHolder(
            ArticleItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        list[position]?.let { (holder as MoviesViewHolder).bind(it) }
    }
}

